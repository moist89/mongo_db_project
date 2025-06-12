package com.tutorial.mongo_db_project.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO{
    @NotBlank
    private String clientId;

    @NotBlank
    private String channel;

    @NotEmpty
    private List<ItemDTO> items;
}