package com.wasanco.ordermanagement.filter;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OrderManagementFilter extends OncePerRequestFilter {
    @Value("${api.key}")
    private String KEY;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String requestApiKey = request.getHeader("apikey");

            if(requestApiKey == null || !requestApiKey.equals(KEY) ){
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                
                // response.getWriter().write("UNAUTHORIZED REQUEST!!", 0, 0);
                return;
            }
        filterChain.doFilter(request, response);
        
        
    }
    
}
