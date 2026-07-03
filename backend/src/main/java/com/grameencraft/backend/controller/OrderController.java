package com.grameencraft.backend.controller;

import com.grameencraft.backend.model.Order;
import com.grameencraft.backend.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        if (order.getId() == null || order.getTotal() == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Order ID and Total are required.");
            return ResponseEntity.badRequest().body(error);
        }

        Order savedOrder = orderRepository.save(order);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("order", savedOrder);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<Order>> getOrdersByUserEmail(@PathVariable String email) {
        return ResponseEntity.ok(orderRepository.findByUserEmail(email));
    }
}
