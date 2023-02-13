package com.wasanco.orderdetail.orderdetailmanagement.apis;

import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import com.wasanco.orderdetail.orderdetailmanagement.dto.ProductDto;

import reactor.core.publisher.Mono;

public class ProductApi {
    private WebClient productRepository = WebClient.create("http://localhost:8082");
    // private WebClient productRepository = WebClient.builder().baseUrl("http://localhost:8082").filter(logRequest()).defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    public void setProductApi(String apikey,String id,String username,String password){
        this.productRepository = WebClient.builder().baseUrl("http://localhost:8082")
        .defaultHeader("apikey", apikey)
        .defaultHeader("id",id)
        .defaultHeader("username",username)
        .defaultHeader("password",password).build() ;
    }

    public ProductDto updateProduct(ProductDto prouductRequestDto) {
        return productRepository.put().uri("/productsDEMO/").body(Mono.just(prouductRequestDto),ProductDto.class).retrieve().bodyToMono(ProductDto.class).block();
    }

}
