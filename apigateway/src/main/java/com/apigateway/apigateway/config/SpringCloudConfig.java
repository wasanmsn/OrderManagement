package com.apigateway.apigateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("orderService",r -> r.path("/orders/**")
                        .uri("lb://Order-Service")
                       )
                .route("productService",r -> r.path("/products/**","/prodcutsDEMO/**")
                        .uri("lb://Product-Service")
                        )
                .route("orderDetailService",r -> r.path("/order-details/**")
                        .uri("lb://Order-Detail-Service")
                        )
                .route("productDEMOService", r -> r.path("/productsDEMO/**")
                        .uri("lb://Product-Service")
                        ) 
                .route("accountService", r -> r.path("/accounts/**").uri("lb://Account-Service")
                        ) 
                .build();
    }

}


