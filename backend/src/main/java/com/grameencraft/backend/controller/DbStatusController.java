package com.grameencraft.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DbStatusController {

    private final DataSource dataSource;

    public DbStatusController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/db-status")
    public ResponseEntity<Map<String, Object>> getDbStatus() {
        Map<String, Object> status = new HashMap<>();
        String dbUrl = System.getenv("DATABASE_URL");
        boolean configured = dbUrl != null && !dbUrl.trim().isEmpty();

        boolean connected = false;
        String error = null;

        try (Connection conn = dataSource.getConnection()) {
            connected = true;
            status.put("databaseProduct", conn.getMetaData().getDatabaseProductName());
        } catch (Exception e) {
            error = e.getMessage();
        }

        status.put("connected", connected);
        status.put("configured", configured);
        status.put("error", error);
        status.put("source", configured && connected ? "postgresql" : "h2-fallback");

        return ResponseEntity.ok(status);
    }
}
