package com.wasanco.ordermanagement;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.wasanco.ordermanagement.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class OrderManagementInterceptor implements HandlerInterceptor {
    @Value("${api.key}")
    private String KEY;

    @Autowired
    private AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println(request.getRequestURL());
        String requestApiKey = request.getHeader("apikey");
        String id = request.getHeader("id");
        String username = request.getHeader("username");
        String password = request.getHeader("password");        

        if(requestApiKey == null || !requestApiKey.equals(KEY) ){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(requestApiKey == null ? "Missing API key" : "Incorrect API key");
            
            // response.getWriter().write("UNAUTHORIZED REQUEST!!", 0, 0);
            return false;
        }
        request.setAttribute("apikey", KEY);
        if(id == null || username == null || password == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing header");
            return false;
        }
        if(!accountService.checkPassword(id, username, password)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid account!");
            return false;
        }
        
            
        return true;
    }
}
