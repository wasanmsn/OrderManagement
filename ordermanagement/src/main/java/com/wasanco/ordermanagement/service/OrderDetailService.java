package com.wasanco.ordermanagement.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wasanco.ordermanagement.dto.OrderDetailDto;
import com.wasanco.ordermanagement.entity.OrderDb;
import com.wasanco.ordermanagement.entity.OrderDetail;
import com.wasanco.ordermanagement.entity.Product;
import com.wasanco.ordermanagement.mapper.OrderDetailMapper;
import com.wasanco.ordermanagement.repository.OrderDbRepository;
import com.wasanco.ordermanagement.repository.OrderDetailRepository;
import com.wasanco.ordermanagement.repository.ProductRepository;
@Service
public class OrderDetailService {
    
    @Autowired
    private OrderDetailRepository repository;
    @Autowired
    private OrderDetailMapper mapper;
    @Autowired
    private OrderDbRepository orderDbRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<OrderDetailDto> getOrderDetails() {
        return mapper.listOrderDetailToDetailDto(repository.findAll());
        
    }

    public OrderDetailDto addOrderDetail(OrderDetailDto orderDetailDto,UUID orderId){
        OrderDetail orderDetail = mapper.orderDetailDtoToOrderDetail(orderDetailDto);
        orderDetail.setAmount(orderDetail.getPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity())));
        OrderDb order = orderDbRepository.findById(orderId).orElse(null);
        if(order == null){
            order = new OrderDb();
            order.setId(orderId);
            order.setTotalAmount(orderDetail.getAmount());

        }else{
            order.setTotalAmount(orderDetail.getAmount().add(order.getTotalAmount()));

        }
        orderDetail.setProduct(productRepository.save(orderDetail.getProduct()));
        orderDetail.setOrder(orderDbRepository.save(order));
        return mapper.orderDetailToOrderDetailDto(repository.saveAndFlush(orderDetail));
    }

    public boolean deleteOrderDetail(UUID id){
        OrderDetail orderDetail = repository.findById(id).orElse(null);
        if(orderDetail == null){
            
            return false;
        }
        OrderDb order = orderDetail.getOrder();
        order.setTotalAmount(order.getTotalAmount().subtract(orderDetail.getAmount()));
        orderDbRepository.save(order);
        repository.deleteById(id);
        return true;
    }
}
