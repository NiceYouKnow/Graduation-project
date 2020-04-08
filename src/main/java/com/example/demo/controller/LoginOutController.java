package com.example.demo.controller;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/*
@auther:yuunfei_fan
登出控制器
*/
@Controller
public class LoginOutController {

    @RequestMapping(value="/loginout", method=RequestMethod.GET)
    public String GoLoginOut(final HttpSession session){
        session.setAttribute("loginEmail", null);
        return "/login";
    }
    


}