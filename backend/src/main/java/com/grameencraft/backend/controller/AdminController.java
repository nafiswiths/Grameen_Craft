package com.grameencraft.backend.controller;

import com.grameencraft.backend.model.Order;
import com.grameencraft.backend.model.Product;
import com.grameencraft.backend.model.User;
import com.grameencraft.backend.repository.OrderRepository;
import com.grameencraft.backend.repository.ProductRepository;
import com.grameencraft.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public AdminController(UserRepository userRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // A. Users Management
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/users/role")
    public ResponseEntity<?> changeUserRole(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String role = request.get("role");

        if (email == null || role == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Email and Role are required.");
            return ResponseEntity.badRequest().body(error);
        }

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "User not found.");
            return ResponseEntity.status(404).body(error);
        }

        User user = userOpt.get();
        user.setRole(role);
        userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "User role updated successfully to " + role);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "User not found.");
            return ResponseEntity.status(404).body(error);
        }

        userRepository.delete(userOpt.get());

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "User deleted successfully.");
        return ResponseEntity.ok(response);
    }

    // B. Orders Management
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }

    @PostMapping("/orders/status")
    public ResponseEntity<?> changeOrderStatus(@RequestBody Map<String, String> request) {
        String id = request.get("id");
        String status = request.get("status");

        if (id == null || status == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Order ID and Status are required.");
            return ResponseEntity.badRequest().body(error);
        }

        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Order not found.");
            return ResponseEntity.status(404).body(error);
        }

        Order order = orderOpt.get();
        order.setStatus(status);
        orderRepository.save(order);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Order status updated successfully to " + status);
        return ResponseEntity.ok(response);
    }

    // C. Products Management
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        Optional<Product> prodOpt = productRepository.findById(id);
        if (prodOpt.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Product not found.");
            return ResponseEntity.status(404).body(error);
        }

        productRepository.delete(prodOpt.get());

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Product deleted successfully.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/products/toggle-stock")
    public ResponseEntity<?> toggleProductStock(@RequestBody Map<String, Object> request) {
        String id = (String) request.get("id");
        Boolean inStock = (Boolean) request.get("inStock");

        if (id == null || inStock == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Product ID and Stock Status are required.");
            return ResponseEntity.badRequest().body(error);
        }

        Optional<Product> prodOpt = productRepository.findById(id);
        if (prodOpt.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Product not found.");
            return ResponseEntity.status(404).body(error);
        }

        Product product = prodOpt.get();
        product.setInStock(inStock);
        productRepository.save(product);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Product stock toggled successfully.");
        return ResponseEntity.ok(response);
    }
}
