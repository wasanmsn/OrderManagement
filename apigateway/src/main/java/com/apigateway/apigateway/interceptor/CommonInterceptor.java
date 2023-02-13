package com.apigateway.apigateway.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CommonInterceptor implements HandlerInterceptor {
    @Value("${api.key}")
    private String KEY;

    private WebClient accountRepository = WebClient.create("http://localhost:8085");
    

    public Boolean checkUsername(String id,String username, String password) {
        return accountRepository.get().uri("/accounts/"+id+"?username="+username+"&password="+password).retrieve().bodyToMono(Boolean.class).block();
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // String requestUrl = request.getRequestURL().toString();
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
        if(!checkUsername(id, username, password)){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid account!");
            return false;
        }
        
            
        return true;
    }
}
