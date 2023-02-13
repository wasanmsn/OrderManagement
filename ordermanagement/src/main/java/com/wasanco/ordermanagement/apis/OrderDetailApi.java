package com.wasanco.ordermanagement.apis;

import java.util.List;
import java.util.UUID;

import org.springframework.web.reactive.function.client.WebClient;

import com.wasanco.ordermanagement.dto.OrderDetailDto;

public class OrderDetailApi {
    private WebClient orderDetailRepository ;
    public void seOrderDetailApi(String apikey,String id,String username,String password){
        this.orderDetailRepository = WebClient.builder().baseUrl("http://localhost:8081")
        .defaultHeader("apikey", apikey)
        .defaultHeader("id",id)
        .defaultHeader("username",username)
        .defaultHeader("password",password).build() ;
    }

    public List<OrderDetailDto> getOrderDetailsByOrderId(UUID id) {
        return orderDetailRepository.get().uri("/order-details?order-id="+id.toString()).retrieve().bodyToFlux(OrderDetailDto.class).collectList().block();
    }

    public OrderDetailDto getOrderDetail(UUID id){
        return orderDetailRepository.get().uri("/order-details/"+id.toString()).retrieve().bodyToMono(OrderDetailDto.class).block();
    }

    public void deleteOrderDetail(UUID id){
        orderDetailRepository.delete().uri("/order-details/"+id.toString()).retrieve().bodyToMono(Void.class);
    }

    public void deleteOrderDetailByOrderId(UUID id){
        orderDetailRepository.delete().uri("/order-details"+id.toString()).retrieve().bodyToMono(Void.class);

    }
}
