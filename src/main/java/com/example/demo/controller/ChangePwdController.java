package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.model.ChangePwd;
import com.example.demo.service.ChangePwdService;
import com.example.demo.tools.Encrypt;

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
public class ChangePwdController{
    @Autowired
    private ChangePwdService changepwdservice;

    @RequestMapping(value="/changepwd", method=RequestMethod.GET)
    public void view(HttpServletRequest request,Map<String, Object> map) 
    {
        
        String email = (String) request.getSession().getAttribute("loginEmail");
        //System.out.println(email);
        map.put("putemail", email);
       //将读取到的数据提交到前端
    }

    @RequestMapping(value="/gochangepwd", method=RequestMethod.POST)
    public String gochangepwd(final Model model,final HttpSession session,
    HttpServletRequest request,
    @RequestParam(value = "oldpassword", required = false) String oldPwd,
    @RequestParam(value = "newpassword", required = false) String newPwd,
    @RequestParam(value = "newpassword1", required = false) String newPwd1
    )//获取前端修改数据
    {
        String oldpwd = Encrypt.stringMD5(oldPwd.trim());
        String newpwd = Encrypt.stringMD5(newPwd.trim());
        String newpwd1 = Encrypt.stringMD5(newPwd1.trim());//MD5加密
        String email = (String) request.getSession().getAttribute("loginEmail");
        
        List<ChangePwd> changepwd = changepwdservice.select(email);
        String pwd = changepwd.get(0).getPassword();
        System.out.println(pwd);
        System.out.println(oldpwd);
        System.out.println(newpwd);
        System.out.println(newpwd1);
        if(pwd.equals(oldpwd)){
            System.out.println("--ok1--");
        }
        if(newpwd.equals(newpwd1)){
            System.out.println("--ok2--");

        }
        //判断密码修改是否合规
        if(pwd.equals(oldpwd) &&newpwd.equals(newpwd1)){
            changepwdservice.insert(email, newpwd);
            //修改密码成功
            System.out.println("--ok--");
            return "success";

        }
        else{
            //修改密码失败
            System.out.println("--no--");
            return "error";
        }

    }
    
    
}