package com.example.demo.service;


import java.sql.Date;

import com.example.demo.mapper.RegisterMapper;
import com.example.demo.model.Register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
@auther:yunfei_fan
*/
@Service

public class RegisterService {

 

@Autowired

private RegisterMapper registerMapper;

 public Integer select(String email){
     Register register = new Register();
     register.setEmail(email);
     //register.setPassword(password);
    Integer userRegister = registerMapper.select(register); 
    return userRegister; //返回的userLogin为0或1
    //注册查询数据库方法

 }
 public void insert(String email, String username, String password, Date birthday, String sex, int age){
    Register register = new Register();
    register.setEmail(email);
    register.setUsername(username);
    register.setPassword(password);
    register.setBirthday(birthday);
    register.setSex(sex);
    register.setAge(age);
    registerMapper.insert(register);
   //注册存入数据库方法
}
}