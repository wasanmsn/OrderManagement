package com.apigateway.apigateway.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CommonInterceptorMvcConfig implements WebMvcConfigurer {
    @Autowired
    CommonInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        registry.addInterceptor(interceptor).excludePathPatterns("/accounts");
    }
}
