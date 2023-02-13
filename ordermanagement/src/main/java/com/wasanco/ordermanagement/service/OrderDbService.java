package com.wasanco.ordermanagement.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.wasanco.ordermanagement.Constants;
import com.wasanco.ordermanagement.apis.OrderDetailApi;
import com.wasanco.ordermanagement.dto.OrderDto;
import com.wasanco.ordermanagement.dto.request.OrderRequestDto;
import com.wasanco.ordermanagement.entity.OrderDb;

import com.wasanco.ordermanagement.exceptions.NotFoundException;
import com.wasanco.ordermanagement.mapper.OrderMapper;
import com.wasanco.ordermanagement.repository.OrderDbRepository;

import com.wasanco.ordermanagement.service.serviceinterface.IOrderDbService;

import jakarta.transaction.Transactional;
import jakarta.servlet.http.HttpServletRequest;


@Service
public class OrderDbService implements IOrderDbService {
    @Autowired
    private OrderDbRepository repository;
    @Autowired
    private OrderMapper mapper;

    @Autowired
    private HttpServletRequest request;

    private OrderDetailApi orderDetailRepository = new OrderDetailApi();
    




    @Override
    public OrderDto getOrderById(UUID id){
        OrderDb order = repository.findById(id).orElse(null);
        return mapper.orderDbToOrderDto(order);
    }

    @Override
    public List<OrderDto> getOrderByIdOrderByCreateAtAsc(UUID id) {
        List<OrderDb> order = repository.findByIdOrderByCreateAtAsc(id);
        return mapper.listOrderDbToOrderDto(order);
    }
    @Override
    public List<OrderDto> getOrderByIdOrderByCreateAtDesc(UUID id) {
        List<OrderDb> order = repository.findByIdOrderByCreateAtDesc(id);
        return mapper.listOrderDbToOrderDto(order);
    }
    @Override
    public List<OrderDto> getOrders() {
        return mapper.listOrderDbToOrderDto(repository.findAll());
    }
    @Override
    public List<OrderDto> getOrdersByProductIdOrderByCreateAtOrderBy(UUID id,Boolean isASC){
        List<OrderDb> order = repository.filterByProductIdOrderByCreateAt(id.toString(),isASC);
        return mapper.listOrderDbToOrderDto(order);
    }

    @Override
    public OrderDto createOrder(OrderRequestDto orderDto){
        OrderDb order = new OrderDb();
        order.setTotalAmount(orderDto.getTotalAmnt());
        return mapper.orderDbToOrderDto(repository.save(order));
         
    }
    @Override
    public  Boolean deleteOrder(UUID id){
        OrderDb order = repository.findById(id).orElse(null); 
        if(order == null){
            return false;
        }
        orderDetailRepository.seOrderDetailApi(request.getHeader("apikey"),request.getHeader("id"),request.getHeader("username"),request.getHeader("password"));
        orderDetailRepository.deleteOrderDetailByOrderId(id);
        order.setStatus(Constants.DELETE);
        repository.save(order);
        return true;
    }
    @Override
    @Transactional
    public OrderDto updateOrder(OrderRequestDto order) throws Exception{
        OrderDb orderDb = repository.findById(order.getId()).orElse(null);
        if(orderDb == null) orderDb.setId(order.getId());
        orderDb.setTotalAmount(order.getTotalAmnt());
        repository.save(orderDb);
        return mapper.orderDbToOrderDto(orderDb);


    }
}
