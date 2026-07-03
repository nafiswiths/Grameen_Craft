package com.grameencraft.backend.controller;

import com.grameencraft.backend.model.Artisan;
import com.grameencraft.backend.repository.ArtisanRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/artisans")
public class ArtisanController {

    private final ArtisanRepository artisanRepository;

    public ArtisanController(ArtisanRepository artisanRepository) {
        this.artisanRepository = artisanRepository;
    }

    @PostMapping
    public ResponseEntity<?> createArtisan(@RequestBody Artisan artisan) {
        if (artisan.getId() == null || artisan.getNameEn() == null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Artisan ID and English Name are required.");
            return ResponseEntity.badRequest().body(error);
        }

        Artisan savedArtisan = artisanRepository.save(artisan);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("artisan", savedArtisan);
        return ResponseEntity.ok(response);
    }
}
