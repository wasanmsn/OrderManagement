package com.wasanco.orderdetail.orderdetailmanagement.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import com.wasanco.orderdetail.orderdetailmanagement.Constants;
import com.wasanco.orderdetail.orderdetailmanagement.apis.OrderDbApi;
import com.wasanco.orderdetail.orderdetailmanagement.apis.ProductApi;
import com.wasanco.orderdetail.orderdetailmanagement.dto.OrderDetailDto;
import com.wasanco.orderdetail.orderdetailmanagement.dto.OrderDto;

import com.wasanco.orderdetail.orderdetailmanagement.dto.request.OrderDetailProductRequestDto;
import com.wasanco.orderdetail.orderdetailmanagement.dto.request.OrderDetailRequestDto;
import com.wasanco.orderdetail.orderdetailmanagement.dto.request.OrderRequestDto;
import com.wasanco.orderdetail.orderdetailmanagement.entity.OrderDetail;

import com.wasanco.orderdetail.orderdetailmanagement.exceptions.NotFoundException;
import com.wasanco.orderdetail.orderdetailmanagement.mapper.OrderDetailMapper;
import com.wasanco.orderdetail.orderdetailmanagement.mapper.OrderMapper;
import com.wasanco.orderdetail.orderdetailmanagement.repository.OrderDetailRepository;
import com.wasanco.orderdetail.orderdetailmanagement.service.serviceinterface.IOrderDetailService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;


@Service
public class OrderDetailService implements IOrderDetailService {
    
    @Autowired
    private OrderDetailRepository repository;
    @Autowired
    private OrderDetailMapper mapper;
    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private OrderMapper orderMapper;

    // private OrderDbApi orderDbRepository = new OrderDbApi(request.getRequest().getHeaders());
    private OrderDbApi orderDbRepository = new OrderDbApi();


    private ProductApi productRepository = new ProductApi();


    @Override
    public List<OrderDetailDto> getOrderDetails() {
        return mapper.listOrderDetailToDetailDto(repository.findAll());
        
    }
    @Override
    @Transactional
    public OrderDetailDto addOrderDetail(OrderDetailProductRequestDto orderDetailDto,UUID orderId){
        OrderDetail orderDetail = mapper.orderDetailDtoToOrderDetail(orderDetailDto.getOrderDetail());
        orderDetail.setAmount(orderDetail.getPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity())));
       // System.out.println(request.getHeader("apikey")+request.getHeader("id")+request.getHeader("username")+request.getHeader("password"));
        //
        orderDbRepository.setOrderDbApi(request.getHeader("apikey"),request.getHeader("id"),request.getHeader("username"),request.getHeader("password"));
        productRepository.setProductApi(request.getHeader("apikey"),request.getHeader("id"),request.getHeader("username"),request.getHeader("password"));
        OrderDto order = orderDbRepository.getOrderById(orderId);
        //
        OrderRequestDto orderRequest = new OrderRequestDto();
        if(order == null){
            orderRequest.setId(orderId);
            orderRequest.setTotalAmnt(orderDetail.getAmount());

        }else{
            orderRequest.setId(order.getId());
            orderRequest.setTotalAmnt(orderDetail.getAmount().add(order.getTotalAmnt()));

        }
        //
        UUID prodId = productRepository.updateProduct(orderDetailDto.getProduct()).getId();
        //
        orderDetail.setOrder(orderDbRepository.updateOrder(orderRequest).getId());
        orderDetail.setProduct(prodId);
        return mapper.orderDetailToOrderDetailDto(repository.saveAndFlush(orderDetail));
    }
    @Override
    @Transactional
    public boolean deleteOrderDetail(UUID id){
        OrderDetail orderDetail = repository.findById(id).orElse(null);
        if(orderDetail == null){
            
            return false;
        }

        orderDbRepository.setOrderDbApi(request.getHeader("apikey"),request.getHeader("id"),request.getHeader("username"),request.getHeader("password"));


        OrderDto order = orderDbRepository.getOrderById(orderDetail.getOrder());
        order.setTotalAmnt(order.getTotalAmnt().subtract(orderDetail.getAmount()));
        orderDbRepository.updateOrder(orderMapper.orderDtoToOrderRequestDto(order));
        orderDetail.setStatus(Constants.DELETE);
        repository.save(orderDetail);

        return true;
    }
    @Override
    @Transactional
    public OrderDetailDto updateOrderDetail(OrderDetailRequestDto orderDetailDto){
        OrderDetail orderDetail = repository.findById(orderDetailDto.getId()).orElse(null);
        OrderRequestDto order = orderMapper.orderDtoToOrderRequestDto(orderDbRepository.getOrderById(orderDetail.getOrder()));
        order.setTotalAmnt(order.getTotalAmnt().subtract(orderDetail.getAmount()));
        orderDetail.setProductName(orderDetailDto.getProductName());
        orderDetail.setPrice(orderDetailDto.getPrice());
        orderDetail.setQuantity(orderDetailDto.getQuantity());
        orderDetail.setAmount(orderDetail.getPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity())));
        order.setTotalAmnt(orderDetail.getAmount().add(order.getTotalAmnt()));
        orderDbRepository.updateOrder(order);
        return mapper.orderDetailToOrderDetailDto(repository.save(orderDetail));
    }
    @Override
    public List<OrderDetailDto> getOrderDetailsByOrderId(UUID id) {
        return mapper.listOrderDetailToDetailDto(repository.findOrderDetailByOrderId(id));
    }
    @Override
    @Transactional
    public boolean deleteOrderDetailByOrderId(UUID id) {
        List<OrderDetail> orderDetails = repository.findOrderDetailByOrderId(id);
        if(orderDetails.isEmpty()){
            
            return false;
        }
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setStatus(Constants.DELETE);
        }
        repository.saveAll(orderDetails);
        return true;
    }
}
