package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.example.demo.service.LoginService;
import com.example.demo.tools.Encrypt;

/*
@auther:yunfei_fan
@登陆控制器
*/
@Controller
public class LoginController{
    @Autowired
    private LoginService loginservice;
    @RequestMapping("/")
    
    public String view(){
    
        return "login";
    } 
    @RequestMapping(value = "/login")
    //@GetMapping(value = "/login")
    public String login(final Model model,final HttpSession session,
     @RequestParam(value = "email1", required = false) String Email,
     @RequestParam(value = "password1", required = false) String Password,
     Map<String, Object> map)//获取前端登陆数据
    {
        Password = Encrypt.stringMD5(Password);//对password MD5加密
        System.out.println("-测试插入数据-");
        Integer userlogin=loginservice.select(Email, Password);//封装查询
        // Login entity=new Login();
        // entity.setUsername( Username);
        // entity.setUsername( Password);
        //loginservice.insert(entity);
        //return "login";
        //map.put("msg", null);
        if(Email != null && Password != null && userlogin == 1){

            session.setAttribute("loginEmail", Email);
            System.out.println("-ok-");
            return "redirect:homepage";//登陆成功跳转页面
        }
        else{
            System.out.println("-no-");
            session.invalidate();
            map.put("msg", "用户密码错误");
            return "login";//登陆失败跳转页面
        }
        
    }
}