package com.example.demo.mapper;


import java.util.List;

import com.example.demo.model.UserNews;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
/*
@auther:yunfei_fan
*/
@Mapper

public interface UserNewsMapper{

 

 @Select("SELECT EMAIL,USERNAME,SEX,BIRTHDAY,AGE FROM TB_USER WHERE EMAIL = #{email}") //查询数据库
 List<UserNews> select(UserNews usernews);
 

}