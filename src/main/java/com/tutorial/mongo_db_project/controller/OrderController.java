package com.tutorial.mongo_db_project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.mongo_db_project.dto.OrderRequestDTO;
import com.tutorial.mongo_db_project.model.Order;
import com.tutorial.mongo_db_project.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController{
    private final OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO dto) {
        log.info("iniciando la creacion de la orden  : {}",dto.toString());
        return ResponseEntity.ok(orderService.createOrder(dto));
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Order>> findOrder() {
        log.info("iniciando la consulta ");

        return ResponseEntity.ok(orderService.findOrder());
    }
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Order>> findbyClientId(@PathVariable  String clientId) {
        log.info("iniciando la consulta por clientId: {} ",clientId);

        return ResponseEntity.ok(orderService.getOrdersByClientId(clientId));
    }
}