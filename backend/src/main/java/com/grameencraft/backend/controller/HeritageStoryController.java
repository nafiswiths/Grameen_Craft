package com.grameencraft.backend.controller;

import com.grameencraft.backend.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HeritageStoryController {

    private final GeminiService geminiService;

    public HeritageStoryController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/heritage-story")
    public ResponseEntity<?> getHeritageStory(@RequestBody Map<String, String> request) {
        String productName = request.get("productName");
        String artisanName = request.get("artisanName");
        String artisanDistrict = request.get("artisanDistrict");
        String category = request.get("category");

        if (productName == null || productName.trim().isEmpty() || 
            artisanName == null || artisanName.trim().isEmpty()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Product name and artisan name are required.");
            return ResponseEntity.badRequest().body(error);
        }

        Map<String, Object> result = geminiService.generateHeritageStory(productName, artisanName, artisanDistrict, category);
        return ResponseEntity.ok(result);
    }
}
