package com.example.demo.mapper;


import java.util.List;

import com.example.demo.model.ChangePwd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
/*
@auther:yunfei_fan
*/
@Mapper

public interface ChangePwdMapper{

 

 @Select("SELECT EMAIL,PASSWORD FROM TB_USER WHERE EMAIL = #{email}") //查询数据库
 List<ChangePwd> select(ChangePwd changepwd);
 @Insert(value="INSERT INTO TB_USER (EMAIL,PASSWORD) VALUES(#{email},#{password})")//插入数据库
 void insert(ChangePwd changepwd);

}