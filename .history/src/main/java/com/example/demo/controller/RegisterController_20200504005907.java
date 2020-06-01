package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpSession;
import com.example.demo.service.RegisterService;
import com.example.demo.tools.Encrypt;

/*
@auther:yunfei_fan
@注册控制器
*/
@Controller
public class RegisterController {
    @Autowired
    private RegisterService registerservice;

    @RequestMapping(value = "/register")
    public String view() {

        return "register";
    }

    @RequestMapping(value = "/user/register")
    public String register(final Model model, final HttpSession session,
            @RequestParam(value = "email", required = false) String Email,
            @RequestParam(value = "username", required = false) String Username,
            @RequestParam( = "password", required = false) String Password,
            @RequestParam(value = "sex", required = false) String Sex,
            @RequestParam(value = "birthday", required = false) Date Birthday,
            @RequestParam(value = "age", required = false) Number Age) throws IOException// 获取前端登陆数据
    {
        int Age1 = Age.intValue();
        Email = Email.trim();
        Username = Username.trim();
        Password = Encrypt.stringMD5(Password.trim());
        Sex = Sex.trim();
        System.out.println("-测试插入数据-");
        Integer userRegister=registerservice.select(Email, Password);//封装查询
        // Login entity=new Login();
        // entity.setUsername( Username);
        // entity.setUsername( Password);
        //loginservice.insert(entity);
        //return "login";
        if(Username != null && Password != null && userRegister != 1){
            registerservice.insert(Email,Username, Password,Birthday,Sex,Age1);
            System.out.println("-yes-");  
            return "login";//注册成功跳转页面
        }
        else{
  
            System.out.println("-no-");
            return "error";//注册失败跳转页面
            
        }
        
    }
}