package com.grameencraft.backend.controller;

import com.grameencraft.backend.repository.ArtisanRepository;
import com.grameencraft.backend.repository.OrderRepository;
import com.grameencraft.backend.repository.ProductRepository;
import com.grameencraft.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SyncController {

    private final UserRepository userRepository;
    private final ArtisanRepository artisanRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public SyncController(UserRepository userRepository, ArtisanRepository artisanRepository,
                          ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.artisanRepository = artisanRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/sync")
    public ResponseEntity<Map<String, Object>> syncAllData() {
        Map<String, Object> data = new HashMap<>();
        
        try {
            data.put("source", "postgresql");
            data.put("users", userRepository.findAll());
            data.put("artisans", artisanRepository.findAll());
            data.put("products", productRepository.findAll());
            data.put("orders", orderRepository.findAll());
        } catch (Exception e) {
            data.put("error", e.getMessage());
            return ResponseEntity.status(500).body(data);
        }

        return ResponseEntity.ok(data);
    }
}
