package com.example.demo.component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/*
@auther:yunfei_fan
@登录拦截器
*/
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        Object user = request.getSession().getAttribute("loginEmail");
        // 如果获取的request的session中的loginUser参数为空（未登录），就返回登录页，否则放行访问
        if(user == null){
            //未登录，给予提示
            request.setAttribute("msg", "无权限请先登录");
            //获取requset返回页回到登陆页面；
            //response.sendRedirect("/");
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }
        else{
            return true;//已登录，通过
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}