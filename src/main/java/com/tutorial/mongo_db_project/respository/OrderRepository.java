package com.tutorial.mongo_db_project.respository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tutorial.mongo_db_project.model.Order;

public interface OrderRepository extends MongoRepository<Order,String>{
    List<Order> findByClientIdOrderByCreatedAtDesc(String clientId);


    
}