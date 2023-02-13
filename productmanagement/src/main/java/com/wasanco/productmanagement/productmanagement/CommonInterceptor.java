package com.wasanco.productmanagement.productmanagement;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.wasanco.productmanagement.productmanagement.apis.AccountApi;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class CommonInterceptor implements HandlerInterceptor {

@Value("${api.key}")
    private String KEY;

    private AccountApi accountService = new AccountApi();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // final String BASE_URL = "http://localhost:8084";
        String key = request.getHeader("apikey");
        if(key == null || !key.equals(KEY)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("API key missing or incorrect");
            return false;
        }
        String id = request.getHeader("id");
        String username = request.getHeader("username");
        String password = request.getHeader("password");        
        if(id == null || username == null || password == null){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing header");
            return false;
        }
        if(!accountService.checkUsername(id, username, password)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid account!");
            return false;
        }
        return true;
    }    
}
