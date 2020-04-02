package com.example.demo.service;


import java.util.List;

import com.example.demo.mapper.UserNewsMapper;
import com.example.demo.model.UserNews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
@auther:yunfei_fan
*/

@Service

public class UserNewsService {

@Autowired

private UserNewsMapper usernewsMapper;

 public List<UserNews> select(String email){
     UserNews usernews = new UserNews();
     usernews.setEmail(email);
     List<UserNews> usernewsinformation = usernewsMapper.select(usernews); 
    return usernewsinformation;
    //查询用户个人信息

 }

}