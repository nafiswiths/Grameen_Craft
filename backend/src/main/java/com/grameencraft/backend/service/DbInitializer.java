package com.grameencraft.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grameencraft.backend.model.Artisan;
import com.grameencraft.backend.model.Order;
import com.grameencraft.backend.model.Product;
import com.grameencraft.backend.model.User;
import com.grameencraft.backend.repository.ArtisanRepository;
import com.grameencraft.backend.repository.OrderRepository;
import com.grameencraft.backend.repository.ProductRepository;
import com.grameencraft.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

@Component
public class DbInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ArtisanRepository artisanRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DbInitializer(UserRepository userRepository, ArtisanRepository artisanRepository,
                         ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.artisanRepository = artisanRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            // Seed Users if empty
            if (userRepository.count() == 0) {
                System.out.println("Seeding default users...");
                userRepository.save(new User("sajid@dhaka.com", "Sajid Ahmed", "+8801712345678", "House 45, Road 12, Dhanmondi", "Dhaka", "buyer", "password123"));
                userRepository.save(new User("rahima@jamalpur.org", "Rahima Begum", "+8801987654321", "Katha Palli, Melandaha", "Jamalpur", "seller", "password123"));
                userRepository.save(new User("admin@grameencraft.org", "GrameenCraft Admin", "+8801700000000", "GrameenCraft Headquarters, Mirpur", "Dhaka", "admin", "adminpassword"));
            }

            // Seed Artisans if empty
            if (artisanRepository.count() == 0) {
                System.out.println("Seeding artisans from JSON...");
                try (InputStream is = new ClassPathResource("artisans.json").getInputStream()) {
                    List<Artisan> artisans = objectMapper.readValue(is, new TypeReference<List<Artisan>>() {});
                    artisanRepository.saveAll(artisans);
                }
            }

            // Seed Products if empty
            if (productRepository.count() == 0) {
                System.out.println("Seeding products from JSON...");
                try (InputStream is = new ClassPathResource("products.json").getInputStream()) {
                    List<Product> products = objectMapper.readValue(is, new TypeReference<List<Product>>() {});
                    productRepository.saveAll(products);
                }
            }

            // Seed Orders if empty
            if (orderRepository.count() == 0) {
                System.out.println("Seeding default orders...");
                orderRepository.save(new Order("ORD-9281", "June 12, 2026", 1, new BigDecimal("4500.00"), "Shipped", "Traditional Jamalpur Nakshi Katha", "sajid@dhaka.com"));
            }

            System.out.println("Database initialization completed successfully.");
        } catch (Exception e) {
            System.err.println("Database initialization warning: " + e.getMessage() + ". This is expected if the PostgreSQL database is not connected.");
        }
    }
}
