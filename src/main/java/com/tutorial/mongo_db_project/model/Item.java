package com.tutorial.mongo_db_project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
public class Item {
    private String sku;
    private String name;
    private int quantity;
    private int price;
}
