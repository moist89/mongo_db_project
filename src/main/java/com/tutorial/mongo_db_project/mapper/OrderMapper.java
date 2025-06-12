package com.tutorial.mongo_db_project.mapper;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.tutorial.mongo_db_project.dto.OrderRequestDTO;
import com.tutorial.mongo_db_project.model.Item;
import com.tutorial.mongo_db_project.model.Order;
import com.tutorial.mongo_db_project.model.StatusHistory;
public class OrderMapper {
    
    public static Order toOrder(OrderRequestDTO dto){
         return Order.builder()
                .id("ORDER-" + UUID.randomUUID())
                .clientId(dto.getClientId())
                .channel(dto.getChannel())
                .createdAt(Instant.now())
                .statusHistories(List.of(
                        StatusHistory.builder()
                                .status("CREATED")
                                .timestamp(Instant.now())
                                .build()
                ))
                .items(dto.getItems().stream().map(i ->
                        Item.builder()
                                .sku(i.getSku())
                                .name(i.getName())
                                .quantity(i.getQuantity())
                                .price(i.getPrice())
                                .build()
                ).toList())
                .build();
    }
}