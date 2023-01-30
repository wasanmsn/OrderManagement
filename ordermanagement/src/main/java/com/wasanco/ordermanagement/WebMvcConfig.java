package com.wasanco.ordermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wasanco.ordermanagement.filter.OrderManagementFilter;
@Configuration
public class WebMvcConfig {
    @Autowired
    OrderManagementFilter filter;

    @Bean
    FilterRegistrationBean<OrderManagementFilter> apiFilter() {
        FilterRegistrationBean<OrderManagementFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;      
    }

}
