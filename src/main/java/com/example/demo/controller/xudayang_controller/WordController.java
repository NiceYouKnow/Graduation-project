package com.example.demo.controller.xudayang_controller;

import java.io.File;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.xudayang_model.Word;
import com.example.demo.service.xudayang_service.WordService;
import com.example.demo.tools.FastDFSClient;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.example.demo.tools.Sha1withRSAUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



@Controller
public class WordController {
    @Autowired
    private WordService wordService;

    // 上传word
    @RequestMapping(value = "/xudayanguploadword") 
    public String view() {
        return "xudayanguploadword";
    }

    @PostMapping(value = "/xudayanguploadwordtitle")
    public String WordUpload(String title, MultipartFile file, HttpServletRequest request) throws Exception {
        Word word = new Word();

        String str = FastDFSClient.uploadFile(file);// 上传文件至文件服务器
        String filepath = FastDFSClient.getResAccessUrl(str);

        // RSA对视频链接加密
        // String singFilepate = Sha1withRSAUtil.sign(filepath);// base64处理
        // try {
        //     boolean flag = Sha1withRSAUtil.verify(singFilepate, filepath);// 验证base64正确
        //     System.out.println(flag);
        //     String efilepath = Sha1withRSAUtil.encrypt(filepath);//通过公钥文件进行加密数据后经过base64处理
        //     return efilepath;
        // } catch (Exception e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }        
        String efilepath = Sha1withRSAUtil.encrypt(filepath);//通过公钥文件进行加密数据后经过base64处理


        String wordName = file.getOriginalFilename(); //获取上传后文件的名字
        word.setPath(efilepath);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        String email = (String) request.getSession().getAttribute("loginEmail");
        word.setEmail(email);//通过email唯一标识
        word.setUploadTime(timestamp);//上传时间
        word.setTitle(title);
        word.setSize(this.getSize(file.getSize()));
        word.setType(this.getFileExt(wordName));//存储Word扩展名
        boolean result = wordService.addWord(word);
        if(result){
            return "homepage";
        }
        else{
            return "error";
        }

    }

    
    //获取Word列表
    @GetMapping(value="/wordlist")
    public String getWordListByUser(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") Integer num, ModelMap model) {
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("uploadTime desc");
        System.out.println("-ok-");
        String email = (String) request.getSession().getAttribute("loginEmail");
        Word word = new Word();
        word.setEmail(email);
        List<Word> wordList = wordService.getWordList(word);
        PageInfo<Word> pageInfo = new PageInfo<Word>(wordList);//使用PageHelper integration分页插件
        model.addAttribute("pageInfo", pageInfo);
        return "videolist";
    }



    //展示word
    @RequestMapping(value="/wordPlayById", method=RequestMethod.GET)
    public String wordPlayById(Integer id, ModelMap model) throws Exception {
        Word oldword = new Word();
        oldword.setId(id);

        Word word = wordService.getWordById(oldword);//查询word
        String filepath=Sha1withRSAUtil.decode(word.getPath());
        model.addAttribute("title", word.getTitle());//返回word名
        model.addAttribute("path", filepath);//返回word链接
        return "wordplay";
    }


    //删除word
    @GetMapping(value = "/deleteWordById")
    public String deleteWordById(Integer id, ModelMap model) {
        
        Word oldword = new Word();
        oldword.setId(id);
        Word word = wordService.getWordById(oldword);//查询要删除的word信息
        if (word != null) {

            try{

                boolean result = FastDFSClient.deleteFile(word.getPath());//删除fastdfs文件服务器上对应的word
                if (result) {
                    wordService.deleteWordById(id);//删除MySQL上的word信息

                        return "forward:/wordlist";
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return "error";

        }
        return "error";
    }


    //下载word
    @GetMapping(value = "/downloadWordById")
    public String downloadVideoById(Integer id, ModelMap model) throws Exception {

        Word oldword = new Word();
        oldword.setId(id);
        Word word = wordService.getWordById(oldword);//查询要下载的word
        // File dir = new File("D:\\down\\video");
        // if (!dir.exists()) {   // 判断目录是否存在     
        //     dir.mkdir();   
        // }
        String filepath=Sha1withRSAUtil.decode(word.getPath());
        File file = new File("D:\\"+word.getTitle()+word.getType());//下载word存储路径
        boolean result = FastDFSClient.downloadFile(filepath,file);
        if(result) {
            System.out.println("下载文件："+file.getName()+" 成功");
            model.addAttribute("msg","下载成功");
            return "success";
        }else{
            System.out.println("下载失败！");
            model.addAttribute("msg","下载失败");
            return "error";
        }
        

    }
     /**
     * 获取文件扩展名
     *
     * @return string
     */
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    // /**
    //  * 依据原始文件名生成新文件名
    //  *
    //  * @return
    //  */
    // private String getName(String fileName) {
    //     Random random = new Random();
    //     return "" + random.nextInt(10000) + System.currentTimeMillis();

    // }

    /**
     * 文件大小，返回kb.mb
     *
     * @return
     */
    private String getSize(long fileLength) {
        String size = "";
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileLength < 1024) {
            size = df.format((double) fileLength) + "BT";
        } else if (fileLength < 1048576) {
            size = df.format((double) fileLength / 1024) + "KB";
        } else if (fileLength < 1073741824) {
            size = df.format((double) fileLength / 1048576) + "MB";
        } else {
            size = df.format((double) fileLength / 1073741824) + "GB";
        }

        return size;

    }
}