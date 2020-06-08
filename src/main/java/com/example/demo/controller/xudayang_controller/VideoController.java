package com.example.demo.controller.xudayang_controller;

import java.io.File;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.xudayang_model.Video;
import com.example.demo.service.xudayang_service.VideoService;
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
public class VideoController {
    @Autowired
    private VideoService videoService;

    // 上传视频
    @RequestMapping(value = "/xudayanguploadvideo") 
    public String view() {
        return "xudayanguploadvideo";
    }

    @PostMapping(value = "/xudayanguploadvideotitle")
    public String VideoUpload(String title, MultipartFile mfile, HttpServletRequest request) throws Exception {
        Video video = new Video();


        String str = FastDFSClient.uploadFile(mfile);// 上传文件至文件服务器
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


        String videoName = mfile.getOriginalFilename(); //获取上传后文件的名字
        video.setPath(efilepath);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        
        String email = (String) request.getSession().getAttribute("loginEmail");
        video.setEmail(email);//通过email唯一标识
        video.setUploadTime(timestamp);//上传时间
        video.setTitle(title);
        video.setSize(this.getSize(mfile.getSize()));
        video.setType(this.getFileExt(videoName));//存储视频扩展名
        boolean result = videoService.addVideo(video);
        if(result){
            return "homepage";
        }
        else{
            return "error";
        }

    }

    
    //获取视频列表
    @GetMapping(value="/videolist")
    public String getVideoListByUser(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") Integer num, ModelMap model) {
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("uploadTime desc");
        System.out.println("-ok-");
        String email = (String) request.getSession().getAttribute("loginEmail");
        Video video = new Video();
        video.setEmail(email);
        List<Video> videoList = videoService.getVideoList(video);
        PageInfo<Video> pageInfo = new PageInfo<Video>(videoList);//使用PageHelper integration分页插件
        model.addAttribute("pageInfo", pageInfo);
        return "videolist";
    }



    //播放视频
    @RequestMapping(value="/videoPlayById", method=RequestMethod.GET)
    public String videoPlayById(Integer id, ModelMap model) throws Exception {
        Video oldvideo = new Video();
        oldvideo.setId(id);

        Video video = videoService.getVideoById(oldvideo);//查询视频
        String filepath=Sha1withRSAUtil.decode(video.getPath());
        model.addAttribute("title", video.getTitle());//返回视频名
        model.addAttribute("path", filepath);//返回播放链接
        return "videoplay";
    }


    //删除视频
    @GetMapping(value = "/deleteVideoById")
    public String deleteVideoById(Integer id, ModelMap model) {
        
        Video oldvideo = new Video();
        oldvideo.setId(id);
        Video video = videoService.getVideoById(oldvideo);//查询要删除的视频信息
        if (video != null) {

            try{

                boolean result = FastDFSClient.deleteFile(video.getPath());//删除fastdfs文件服务器上对应的视频
                if (result) {
                    videoService.deleteVideoById(id);//删除MySQL上的视频信息

                        return "forward:/videolist";
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return "error";

        }
        return "error";
    }


    //下载视频
    @GetMapping(value = "/downloadVideoById")
    public String downloadVideoById(Integer id, ModelMap model) throws Exception {

        Video oldvideo = new Video();
        oldvideo.setId(id);
        Video video = videoService.getVideoById(oldvideo);//查询要下载的视频
        // File dir = new File("D:\\down\\video");
        // if (!dir.exists()) {   // 判断目录是否存在     
        //     dir.mkdir();   
        // }
        String filepath=Sha1withRSAUtil.decode(video.getPath());
        File file = new File("D:\\"+video.getTitle()+video.getType());//下载视频存储路径
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