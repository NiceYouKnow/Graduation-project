package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.example.demo.model.UserNews;
import com.example.demo.service.UserNewsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
@auther:yunfei_fan
@修改控制器
*/
@Controller
public class UserNewsController{
    @Autowired
    private UserNewsService usernewsservice;
    
    @RequestMapping(value="/usernews", method=RequestMethod.GET)
    public void view(HttpServletRequest request,Map<String, Object> map) 
    {
        //显示已存储的个人信息；
        String email = (String) request.getSession().getAttribute("loginEmail");
        //System.out.println(email);
        List<UserNews> usernews = usernewsservice.select(email);
       
        map.put("putemail", usernews.get(0).getEmail());
       // System.out.println(changeuser.get(0));
       //将读取到的数据提交到前端
        map.put("putusername",usernews.get(0).getUsername());
        map.put("putsex",usernews.get(0).getSex());
        map.put("putbirthday",usernews.get(0).getBirthday());
        map.put("putage",usernews.get(0).getAge());
    }
    
    
}