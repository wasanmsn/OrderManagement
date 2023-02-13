package com.wasanco.productmanagement.productmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class CommonInterceptorWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    CommonInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO Auto-generated method stub
        registry.addInterceptor(interceptor).excludePathPatterns("/accounts");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/products/**")
        .allowedOrigins("http://localhost:9999")
        .allowedMethods("GET", "POST", "PUT", "DELETE")
        .allowedHeaders("*")
        .allowCredentials(true);
    }
    
}
