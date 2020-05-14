package com.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**

 * 如果项目需要使用war发布，需要创建这个类，作为web程序的入口

 * @author ranger

 *

 */

public class ServletInitializer extends SpringBootServletInitializer {

 

@Override

protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

//表示获得web的请求时，调用Application类的实现

return builder.sources(DemoApplication.class);

}

}