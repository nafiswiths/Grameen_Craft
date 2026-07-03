package com.grameencraft.backend.controller;

import com.grameencraft.backend.model.Product;
import com.grameencraft.backend.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        Optional<Product> prodOpt = productRepository.findById(id);
        if (prodOpt.isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Product not found");
            return ResponseEntity.status(404).body(error);
        }
        return ResponseEntity.ok(prodOpt.get());
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        if (product.getId() == null || product.getNameEn() == null || product.getPrice() == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Product ID, English Name, and Price are required.");
            return ResponseEntity.badRequest().body(error);
        }

        Product savedProduct = productRepository.save(product);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("product", savedProduct);
        return ResponseEntity.ok(response);
    }
}
