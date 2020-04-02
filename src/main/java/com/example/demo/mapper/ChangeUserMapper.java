package com.example.demo.mapper;


import java.util.List;

import com.example.demo.model.ChangeUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
/*
@auther:yunfei_fan
*/
@Mapper

public interface ChangeUserMapper{

 

 @Select("SELECT EMAIL,USERNAME,SEX,BIRTHDAY,AGE FROM TB_USER WHERE EMAIL = #{email}") //查询数据库
 List<ChangeUser> select(ChangeUser changeuser);
 @Insert(value="INSERT INTO TB_USER (EMAIL,USERNAME,SEX,BIRTHDAY,AGE) VALUES(#{email},#{username},#{sex},#{birthday},#{age})")//插入数据库
 void insert(ChangeUser changeuser);

}