package com.apigateway.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;

import org.springframework.beans.factory.annotation.Value;

import reactor.core.publisher.Mono;

@Component
public class ApiValidateGlobalFilter implements GlobalFilter,GatewayFilter {
    @Value("${api.key}")
    private String KEY;

    private WebClient accountRepository = WebClient.create("http://localhost:8085");
    private static final String HEADER_APIKEY = "apikey";
    private static final String HEADER_ID = "id";
    private static final String HEADER_USERNAME = "username";
    private static final String HEADER_PASSWORD = "password";

    public Boolean checkUsername(String id,String username, String password) {
        return accountRepository.get().uri("/accounts/"+id+"?username="+username+"&password="+password).retrieve().bodyToMono(Boolean.class).block();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // TODO Auto-generated method stub
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String headerValue = headers.getFirst(HEADER_APIKEY);

        ServerHttpRequest newRequest = request.mutate().header(HEADER_APIKEY, headerValue).build();

        String headerValueId = headers.getFirst(HEADER_ID);

        newRequest = request.mutate().header(HEADER_ID, headerValueId).build();

        String headerValueUsername = headers.getFirst(HEADER_USERNAME);

        newRequest = request.mutate().header(HEADER_USERNAME, headerValueUsername).build();

        String headerValuePassword = headers.getFirst(HEADER_PASSWORD);

        newRequest = request.mutate().header(HEADER_PASSWORD, headerValuePassword).build();
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
        if(headerValue == null || !headerValue.equals(KEY)){
            throw new RuntimeException("header:"+"apikey");
        }
        if(!checkUsername(headerValueId,headerValueUsername,headerValuePassword)){
            throw new RuntimeException("Error:"+"Invalid");
        }
        return chain.filter(newExchange);
    }
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("orders", r -> r.header("apikey")
            .filters(f -> f.filter(new ApiValidateGlobalFilter()))
            .uri("lb://Order-Service"))
        .route("products", r -> r.header("apikey")
            .filters(f -> f.filter(new ApiValidateGlobalFilter()))
            .uri("lb://Product-Service"))
        .route("order-details", r -> r.header("apikey")
            .filters(f -> f.filter(new ApiValidateGlobalFilter()))
            .uri("lb://Order-Detail-Service"))
    .build();
    }
}


