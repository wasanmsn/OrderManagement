package com.wasanco.orderdetail.orderdetailmanagement.apis;

import java.util.List;
import java.util.UUID;

import org.aopalliance.intercept.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import com.wasanco.orderdetail.orderdetailmanagement.CommonInterceptor;
import com.wasanco.orderdetail.orderdetailmanagement.dto.OrderDto;
import com.wasanco.orderdetail.orderdetailmanagement.dto.request.OrderRequestDto;
import com.wasanco.orderdetail.orderdetailmanagement.exceptions.NotFoundException;

import reactor.core.publisher.Mono;



public class OrderDbApi {
    // WebClient.create("http://localhost:8080")

    private WebClient orderDbRepository  = WebClient.create("http://localhost:8080");

    public void setOrderDbApi(String apikey,String id,String username,String password){
        this.orderDbRepository = WebClient.builder().baseUrl("http://localhost:8080")
        .defaultHeader("apikey", apikey)
        .defaultHeader("id",id)
        .defaultHeader("username",username)
        .defaultHeader("password",password).build() ;
    }



    public  OrderDto getOrderById(UUID id) {

        return orderDbRepository.get().uri("/orders/"+id.toString()).retrieve().bodyToMono(OrderDto.class).block();
    } 

    public  List<OrderDto> getOrders() {
        return orderDbRepository.get().uri("/orders/").retrieve().bodyToFlux(OrderDto.class).collectList().block();
    }

    public  void deleteOrder(UUID id){
        orderDbRepository.delete().uri("/orders/"+id.toString()).retrieve().bodyToMono(Void.class);
    }

    public  OrderDto updateOrder(OrderRequestDto order){
        return orderDbRepository.put().uri("/orders/").body(Mono.just(order),OrderRequestDto.class).retrieve()/*.onStatus(
            httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus), error -> Mono.error(new NotFoundException("Order not found."))) */
            .bodyToMono(OrderDto.class).block();
    }

    public  OrderDto createOrder(OrderRequestDto order) {
        return orderDbRepository.put().uri("/orders/").body(Mono.just(order),OrderRequestDto.class).retrieve().bodyToMono(OrderDto.class).block();
        
    }
}
