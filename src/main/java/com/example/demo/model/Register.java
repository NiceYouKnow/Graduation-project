package com.example.demo.model;
/*
@auther yunfei_fan
@注册实体
*/

import java.sql.Date;

public class Register{
    private String email;
    private String username;
    private String password;
    private String sex;
    private Date birthday;
    private int age;

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username=username;

    }
    public String getPassword(){
        return this.password;
    
    }
    public void setPassword(String password){
        this.password=password;

    }
    public String getSex(){
        return this.sex;
    
    }
    public void setSex(String sex){
        this.sex=sex;
    
    }
    public Date getBirthday(){
        return this.birthday;
    
    }
    public void setBirthday(Date birthday){
        this.birthday=birthday;

    } 
    public int getAge(){
        return this.age;

    }
    public void setAge(int age){
        this.age=age;
    }


}