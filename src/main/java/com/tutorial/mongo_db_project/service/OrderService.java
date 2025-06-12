package com.tutorial.mongo_db_project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tutorial.mongo_db_project.dto.OrderRequestDTO;
import com.tutorial.mongo_db_project.mapper.OrderMapper;
import com.tutorial.mongo_db_project.model.Order;
import com.tutorial.mongo_db_project.respository.OrderRepository;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class OrderService{
    private final OrderRepository orderRepository;


    public Order createOrder(OrderRequestDTO orderRequestDTO){
        Order order = OrderMapper.toOrder(orderRequestDTO);
        return orderRepository.save(order);
    }


    public List<Order> findOrder() {
        
        return orderRepository.findAll();   
    }
    public Page<Order> getOrdersByClientId(String clientId,int page , int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        return orderRepository.findByClientIdOrderByCreatedAtDesc(clientId,pageable);
    }
    public Page<Order> getOrdersByStatus(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return orderRepository.findByCurrentStatus(status, pageable);
    }
    
    public Page<Order> getOrdersByChannel(String channel, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return orderRepository.findByChannel(channel, pageable);
    }

}