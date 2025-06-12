package com.tutorial.mongo_db_project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.mongo_db_project.service.OrderAnalyticsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/analytics")
public class OrderAnalyticsCOntroller {
    private OrderAnalyticsService analyticsService;

    @GetMapping("/avg-transition-time")
    public ResponseEntity<Double> getAverageTransitionTime(
            @RequestParam String from,
            @RequestParam String to) {
        log.info(" iniciando la  consulta average from : {} , to: {} ",
                from, to);
        return ResponseEntity.ok(

                analyticsService.getAverageTransitionTime(from, to));
    }

    @GetMapping("/top-products")
    public ResponseEntity<List<org.bson.Document>> getTopProductsByChannel(
            @RequestParam String channel) {
                log.info(" iniciando la  consulta top 5 products by channel : {} ",
                channel);
        return ResponseEntity.ok(analyticsService.getTop5ProductsByChannel(channel));
    }

}