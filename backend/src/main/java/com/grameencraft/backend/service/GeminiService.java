package com.grameencraft.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.HashMap;
import java.util.Map;

@Service
public class GeminiService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Object> generateHeritageStory(String productName, String artisanName, String artisanDistrict, String category) {
        Map<String, Object> responseMap = new HashMap<>();
        String apiKey = System.getenv("GEMINI_API_KEY");

        if (apiKey == null || apiKey.trim().isEmpty()) {
            responseMap.put("story", getFallbackStory(productName, category, artisanDistrict));
            responseMap.put("isFallback", true);
            responseMap.put("errorMsg", "GEMINI_API_KEY environment variable is not configured.");
            return responseMap;
        }

        try {
            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String prompt = String.format(
                "Write a beautiful, poetic, and heartwarming storytelling description for a traditional handmade craft called \"%s\" (Category: %s) crafted by artisan \"%s\" from the %s district of Bangladesh. " +
                "Include elements of cultural heritage (like the historic origin, raw materials like jute, bamboo, cotton, or clay, and traditional stitching/weaving/molding methods). " +
                "Ensure the tone is warm, elegant, and authentic, emphasizing how this product supports rural artisans and preserves Bangladesh's heritage. " +
                "Keep it engaging and under 150 words.",
                productName, category, artisanName, artisanDistrict != null ? artisanDistrict : "rural"
            );

            // Construct payload using Jackson
            ObjectNode rootNode = objectMapper.createObjectNode();
            ArrayNode contentsArray = rootNode.putArray("contents");
            ObjectNode contentObj = contentsArray.addObject();
            ArrayNode partsArray = contentObj.putArray("parts");
            ObjectNode partObj = partsArray.addObject();
            partObj.put("text", prompt);

            ObjectNode generationConfig = rootNode.putObject("generationConfig");
            generationConfig.put("temperature", 0.7);

            HttpEntity<String> entity = new HttpEntity<>(rootNode.toString(), headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            JsonNode responseJson = objectMapper.readTree(response.getBody());
            String storyText = responseJson.path("candidates")
                    .path(0)
                    .path("content")
                    .path("parts")
                    .path(0)
                    .path("text")
                    .asText();

            if (storyText == null || storyText.trim().isEmpty()) {
                throw new Exception("Received empty response from Gemini API");
            }

            responseMap.put("story", storyText.trim());
            responseMap.put("isFallback", false);

        } catch (Exception e) {
            System.err.println("Gemini API Request Error: " + e.getMessage());
            responseMap.put("story", getFallbackStory(productName, category, artisanDistrict));
            responseMap.put("isFallback", true);
            responseMap.put("errorMsg", e.getMessage());
        }

        return responseMap;
    }

    private String getFallbackStory(String productName, String category, String artisanDistrict) {
        Map<String, String> defaultStories = new HashMap<>();
        defaultStories.put("Nakshi Katha", "This exquisite quilt is a canvas of dreams woven together by Rahima Begum from Jamalpur. Passed down through maternal lineages, each tiny running stitch represents an evening of laughter, rain, and quiet resilience, telling stories of monsoon nights and golden harvests.");
        defaultStories.put("Traditional Jute Bag", "Crafted from golden jute fibers by Jahanara Bibi in Mymensingh, this bag carries the essence of the Shitalakshya riverbanks. Hand-spun, organic, and dyed with plant extracts, it represents the heart of eco-friendly, timeless Bengali craftsmanship.");
        defaultStories.put("Terracotta Clay Pots", "Molded on the potter's wheel by Subal Paul from Sonargaon, these clay pots capture the ancient soul of Lalmai hills clay. Baked in traditional earthen kilns, every curve is a testament to five generations of pottery heritage.");
        defaultStories.put("Bamboo Utility Baskets", "Hand-plaited from wild green bamboo by Abdul Majid in Sylhet, this basket is a marvel of rural engineering. Light, durable, and organic, it carries the whispers of tea garden winds and rural simple living.");

        String story = defaultStories.get(category);
        if (story == null) {
            story = defaultStories.get(productName);
        }
        if (story == null) {
            String district = artisanDistrict != null ? artisanDistrict : "rural Bangladesh";
            story = String.format("This exquisite creation was hand-crafted with traditional methods passed down through generations of artisans in %s. Each piece is completely unique, carrying the authentic heritage, spirit, and warmth of village craftsmanship.", district);
        }
        return story;
    }
}
