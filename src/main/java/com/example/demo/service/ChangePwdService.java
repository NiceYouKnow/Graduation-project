package com.example.demo.service;


import java.util.List;

import com.example.demo.mapper.ChangePwdMapper;
import com.example.demo.model.ChangePwd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
@auther:yunfei_fan
*/

@Service

public class ChangePwdService {

@Autowired

private ChangePwdMapper changepwdMapper;

 public List<ChangePwd> select(String email){
     ChangePwd changepwd = new ChangePwd();
     changepwd.setEmail(email);
     List<ChangePwd> changepwdinformation = changepwdMapper.select(changepwd); 
    return changepwdinformation;
    //查询用户密码信息

 }
 public void insert(String email, String password){
    ChangePwd changepwd = new ChangePwd();
    changepwd.setEmail(email);
    changepwd.setPassword(password);;
   //修改存入数据库方法
}
}