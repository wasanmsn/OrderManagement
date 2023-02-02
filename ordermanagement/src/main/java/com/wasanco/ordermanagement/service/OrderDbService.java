package com.wasanco.ordermanagement.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wasanco.ordermanagement.Constants;
import com.wasanco.ordermanagement.dto.OrderDto;
import com.wasanco.ordermanagement.dto.request.OrderRequestDto;
import com.wasanco.ordermanagement.entity.OrderDb;
import com.wasanco.ordermanagement.entity.OrderDetail;
import com.wasanco.ordermanagement.exceptions.NotFoundException;
import com.wasanco.ordermanagement.mapper.OrderMapper;
import com.wasanco.ordermanagement.repository.OrderDbRepository;
import com.wasanco.ordermanagement.repository.OrderDetailRepository;
@Service
public class OrderDbService {
    @Autowired
    private OrderDbRepository repository;
    @Autowired
    private OrderMapper mapper;
    @Autowired
    private OrderDetailRepository orderDetailRepository;



    public List<OrderDto> getOrderByIdOrderByCreateAtAsc(UUID id) {
        List<OrderDb> order = repository.findByIdOrderByCreateAtAsc(id);
        return mapper.listOrderDbToOrderDto(order);
    }

    public List<OrderDto> getOrderByIdOrderByCreateAtDesc(UUID id) {
        List<OrderDb> order = repository.findByIdOrderByCreateAtDesc(id);
        return mapper.listOrderDbToOrderDto(order);
    }

    public List<OrderDto> getOrders() {
        return mapper.listOrderDbToOrderDto(repository.findAll());
    }

    public List<OrderDto> getOrdersByProductIdOrderByCreateAtOrderBy(UUID id,Boolean isASC){
        List<OrderDb> order = repository.filterByProductIdOrderByCreateAt(id.toString(),isASC);
        return mapper.listOrderDbToOrderDto(order);
    }


    public OrderDto createOrder(OrderDto orderDto){
        return mapper.orderDbToOrderDto(repository.save(mapper.orderDtoToOrderDb(orderDto)));
         
    }

    public  Boolean deleteOrder(UUID id){
        OrderDb order = repository.findById(id).orElse(null); 
        if(order == null){
            return false;
        }
        List<OrderDetail> orderDetails = order.getOrderDetail();
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setStatus(Constants.DELETE);
            orderDetailRepository.save(orderDetail);
        }
        order.setStatus(Constants.DELETE);
        return true;
    }

    public OrderDto updateOrder(OrderRequestDto order,UUID id) throws Exception{
        OrderDb orderDb = repository.findById(id).orElse(null);
        if(orderDb == null){
            throw new NotFoundException("Order not found. "+id);
        }
        orderDb.setTotalAmount(order.getTotalAmnt());
        repository.save(orderDb);
        return mapper.orderDbToOrderDto(orderDb);


    }
}
