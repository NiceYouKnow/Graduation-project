package com.example.demo.config;
import com.example.demo.component.LoginHandlerInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
@auther:yunfei_fan
@配置文件
*/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/homepage").setViewName("homepage");//映射的路径
        registry.addViewController("/login").setViewName("/");//映射的路径
        registry.addViewController("/changeuser").setViewName("changeuser");//映射的路径
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //添加拦截的请求，并排除几个不拦截的请求
        InterceptorRegistration registration = registry.addInterceptor(new LoginHandlerInterceptor());
        registration.addPathPatterns("/**");
        registration.excludePathPatterns("login.html","/","/login","/register","/static/","/user/register");
    }
}