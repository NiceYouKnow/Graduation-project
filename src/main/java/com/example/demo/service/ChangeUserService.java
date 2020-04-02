package com.example.demo.service;


import java.sql.Date;
import java.util.List;

import com.example.demo.mapper.ChangeUserMapper;
import com.example.demo.model.ChangeUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
@auther:yunfei_fan
*/

@Service

public class ChangeUserService {

@Autowired

private ChangeUserMapper changeuserMapper;

 public List<ChangeUser> select(String email){
     ChangeUser changeuser = new ChangeUser();
     changeuser.setEmail(email);
     List<ChangeUser> changeuserinformation = changeuserMapper.select(changeuser); 
    return changeuserinformation;
    //查询用户个人信息

 }
 public void insert(String email, String username, Date birthday, String sex, int age){
    ChangeUser changeuser = new ChangeUser();
    changeuser.setEmail(email);
    changeuser.setUsername(username);
    changeuser.setBirthday(birthday);
    changeuser.setSex(sex);
    changeuser.setAge(age);
    changeuserMapper.insert(changeuser);
   //修改存入数据库方法
}
}