package com.example.demo.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.model.ChangeUser;
import com.example.demo.service.ChangeUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/*
@auther:yunfei_fan
@修改控制器
*/
@Controller
public class ChangeUserController{
    @Autowired
    private ChangeUserService changeuserservice;
    // @RequestMapping(value = "/changeuser")
    // public String view(){
    
    //     return "changeuser";
    // } 
    @RequestMapping(value="/changeuser", method=RequestMethod.GET)
    public void view(HttpServletRequest request,Map<String, Object> map) 
    {
        //显示已存储的个人信息；
        String email = (String) request.getSession().getAttribute("loginEmail");
        //System.out.println(email);
        List<ChangeUser> changeuser = changeuserservice.select(email);
        //System.out.println(changeuserservice.select(email));
        //System.out.println(changeuser);
        map.put("putemail", changeuser.get(0).getEmail());
       // System.out.println(changeuser.get(0));
       //将读取到的数据提交到前端
        map.put("putusername",changeuser.get(0).getUsername());
        map.put("putsex",changeuser.get(0).getSex());
        map.put("putbirthday",changeuser.get(0).getBirthday());
        map.put("putage",changeuser.get(0).getAge());
    }

    @RequestMapping(value="/gochangeuser", method=RequestMethod.POST)
    public String gochangeuser(final Model model,final HttpSession session,
    @RequestParam(value = "email", required = false) String Email,
    @RequestParam(value = "username", required = false) String Username,
    @RequestParam(value = "sex", required = false) String Sex,
    @RequestParam(value = "birthday", required = false) Date Birthday,
    @RequestParam(value = "age", required = false) Number Age)//获取前端修改数据
    {
        Email = Email.trim();
        Username = Email.trim();
        Sex = Sex.trim();
        int Age1 = Age.intValue();
        changeuserservice.insert(Email, Username, Birthday, Sex, Age1);
        System.out.println("-yes-");  
        return "changeuser";//修改信息后跳转页面

    }
    
    
}