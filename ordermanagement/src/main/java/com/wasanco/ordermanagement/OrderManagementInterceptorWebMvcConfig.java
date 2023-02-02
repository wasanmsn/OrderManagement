package com.wasanco.ordermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OrderManagementInterceptorWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    OrderManagementInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        registry.addInterceptor(interceptor).excludePathPatterns("/accounts");
    }
    
}
