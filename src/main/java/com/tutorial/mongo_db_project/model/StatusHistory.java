package com.tutorial.mongo_db_project.model;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusHistory {
    private String status;
    private Instant timestamp;
}
