package com.example.demo.model;
/*
@auther yunfei_fan
登陆实体
*/
public class Login {
    private String email;
    private String password;

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    @Override //重载toString
    public String toString(){
        return "Login{"+
                "username="+ email + '\'' +
                ",password='" + password + '\'' +
                '}';
    }
    

}