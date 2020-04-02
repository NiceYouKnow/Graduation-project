package com.example.demo.service;

import com.example.demo.mapper.LoginMapper;
import com.example.demo.model.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
@auther:yunfei_fan
*/
@Service

public class LoginService {

 

@Autowired

private LoginMapper loginMapper;

 public Integer select(String email, String password){
     Login login = new Login();
     login.setEmail(email);
     login.setPassword(password);
    Integer userLogin = loginMapper.select(login); 
    return userLogin; //返回的userLogin为0或1
    //登陆查询方法

 }
}