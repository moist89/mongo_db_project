package com.tutorial.mongo_db_project.respository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.tutorial.mongo_db_project.model.Order;

public interface OrderRepository extends MongoRepository<Order,String>{
    Page<Order> findByClientIdOrderByCreatedAtDesc(String clientId, Pageable pageable);
    Page<Order> findByCurrentStatus(String currentStatus, Pageable pageable);
    Page<Order> findByChannel(String channel, Pageable pageable);

    
}