package com.example.demo.mapper;


import com.example.demo.model.Register;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
/*
@auther:yunfei_fan
*/
@Mapper

public interface RegisterMapper {

 

 @Select("SELECT count(*) FROM TB_USER WHERE EMAIL = #{email}") //查询数据库
 Integer select(Register register);
 @Insert(value="INSERT INTO TB_USER (EMAIL,USERNAME,PASSWORD,SEX,BIRTHDAY,AGE) VALUES(#{email},#{username},#{password},#{sex},#{birthday},#{age})")//插入数据库
 void insert(Register register);

}