package com.tutorial.mongo_db_project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tutorial.mongo_db_project.dto.OrderRequestDTO;
import com.tutorial.mongo_db_project.mapper.OrderMapper;
import com.tutorial.mongo_db_project.model.Order;
import com.tutorial.mongo_db_project.respository.OrderRepository;

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
    public List<Order> getOrdersByClientId(String clientId) {
        return orderRepository.findByClientIdOrderByCreatedAtDesc(clientId);
    }

}