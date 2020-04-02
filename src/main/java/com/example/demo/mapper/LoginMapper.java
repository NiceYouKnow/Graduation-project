package com.example.demo.mapper;


import com.example.demo.model.Login;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
/*
@auther:yunfei_fan
*/
@Mapper

public interface LoginMapper{

 

 @Select("SELECT count(*) FROM TB_USER WHERE EMAIL = #{email} and PASSWORD = #{password}") //查询数据库
 Integer select(Login login);//引入Login实体


}