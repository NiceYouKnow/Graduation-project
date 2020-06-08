package com.example.demo.controller.xudayang_controller;

import java.io.File;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.xudayang_model.Image;
import com.example.demo.service.xudayang_service.ImageService;
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
public class ImageController {
    @Autowired
    private ImageService imageService;

    // 上传图片
    @RequestMapping(value = "/xudayanguploadimage") 
    public String view() {
        return "xudayanguploadimage";
    }

    @PostMapping(value = "/xudayanguploadimagetitle")
    public String ImageUpload(String title, MultipartFile file, HttpServletRequest request) throws Exception {
        Image image = new Image();

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


        String imageName = file.getOriginalFilename(); //获取上传后文件的名字
        image.setPath(efilepath);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        String email = (String) request.getSession().getAttribute("loginEmail");
        image.setEmail(email);//通过email唯一标识
        image.setUploadTime(timestamp);//上传时间
        image.setTitle(title);
        image.setSize(this.getSize(file.getSize()));
        image.setType(this.getFileExt(imageName));//存储视频扩展名
        boolean result = imageService.addImage(image);
        if(result){
            return "homepage";
        }
        else{
            return "error";
        }

    }

    
    //获取图片列表
    @GetMapping(value="/imagelist")
    public String getImageListByUser(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") Integer num, ModelMap model) {
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("uploadTime desc");
        System.out.println("-ok-");
        String email = (String) request.getSession().getAttribute("loginEmail");
        Image image = new Image();
        image.setEmail(email);
        List<Image> imageList = imageService.getImageList(image);
        PageInfo<Image> pageInfo = new PageInfo<Image>(imageList);//使用PageHelper integration分页插件
        model.addAttribute("pageInfo", pageInfo);
        return "imagelist";
    }



    //展示图片
    @RequestMapping(value="/imagePlayById", method=RequestMethod.GET)
    public String imagePlayById(Integer id, ModelMap model) throws Exception {
        Image oldimage = new Image();
        oldimage.setId(id);

        Image image = imageService.getImageById(oldimage);//查询图片
        String filepath=Sha1withRSAUtil.decode(image.getPath());
        model.addAttribute("title", image.getTitle());//返回图片名
        model.addAttribute("path", filepath);//返回图片链接
        return "imageplay";
    }


    //删除视频
    @GetMapping(value = "/deleteImageById")
    public String deleteImageById(Integer id, ModelMap model) {
        
        Image oldimage = new Image();
        oldimage.setId(id);
        Image image = imageService.getImageById(oldimage);//查询要删除的图片信息
        if (image != null) {

            try{

                boolean result = FastDFSClient.deleteFile(image.getPath());//删除fastdfs文件服务器上对应的图片
                if (result) {
                    imageService.deleteImageById(id);//删除MySQL上的图片信息

                        return "forward:/imagelist";
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return "error";

        }
        return "error";
    }


    //下载图片
    @GetMapping(value = "/downloadImageById")
    public String downloadImageById(Integer id, ModelMap model) throws Exception {

        Image oldimage = new Image();
        oldimage.setId(id);
        Image image = imageService.getImageById(oldimage);//查询要下载的图片
        // File dir = new File("D:\\down\\video");
        // if (!dir.exists()) {   // 判断目录是否存在     
        //     dir.mkdir();   
        // }
        String filepath=Sha1withRSAUtil.decode(image.getPath());
        File file = new File("D:\\"+image.getTitle()+image.getType());//下载视频存储路径
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