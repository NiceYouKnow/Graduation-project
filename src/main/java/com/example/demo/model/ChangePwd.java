package com.example.demo.model;

/*
@auther:yunfei_fan
@修改密码实体
*/
public class ChangePwd {
    private String email;
    private String password;
    public void setEmail(String emial){
        this.email=emial;

    }
    public String getEmail(){
        return this.email;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return this.password;
    }
}