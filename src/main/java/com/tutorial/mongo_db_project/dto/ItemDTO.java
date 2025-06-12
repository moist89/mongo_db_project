package com.tutorial.mongo_db_project.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
       @NotBlank
        private String sku;

        @NotBlank
        private String name;

        @Min(1)
        private int quantity;

        @Min(0)
        private int price;
}
