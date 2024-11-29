package com.example.demo.Service.Activity;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Entity.FoodMacros;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FoodMacrosService {

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public FoodMacrosService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<FoodMacros> getNutritionData(String searchTerm) {
        try {
            // URL encode the search term to ensure it's correctly formatted for the URL
            String encodedSearchTerm = URLEncoder.encode(searchTerm, StandardCharsets.UTF_8);

            // Construct the URL using the searchByKeyword API endpoint
            String url = apiUrl + "/searchByKeyword?keyword=" + encodedSearchTerm + "&page=1";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "application/json");
            headers.set("Content-Type", "application/json");
            headers.set("x-rapidapi-ua", "RapidAPI-Playground");
            headers.set("x-rapidapi-key", apiKey);
            headers.set("x-rapidapi-host", "myfitnesspal2.p.rapidapi.com");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Call the API and get the response
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            // Parse the response and return a list of FoodMacros
            return parseApiResponse(response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.err.println("API request failed: " + e.getStatusCode());
            return new ArrayList<>();  // Return an empty list in case of an error
        }
    }

    private List<FoodMacros> parseApiResponse(String responseBody) {
        try {
            // Create ObjectMapper to parse the JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            
            // Parse the response body into a JsonNode (this represents the root of the JSON response)
            JsonNode rootNode = objectMapper.readTree(responseBody);
            
            JsonNode foodsNode = rootNode.path("foods");
            
            List<FoodMacros> foodList = new ArrayList<>();
            
            // Loop through each food item in the "foods" array
            for (JsonNode foodNode : foodsNode) {
                // Create a new FoodMacros object
                FoodMacros food = new FoodMacros();
                
                // Extract data for each food item and set the corresponding fields
                food.setName(foodNode.path("name").asText());
                food.setBrand(foodNode.path("brand").asText());
                food.setServingSize(foodNode.path("servingSize").asText());
                food.setCalories(foodNode.path("calories").asInt());
                food.setFat(foodNode.path("fat").asText());
                food.setCarbs(foodNode.path("carbs").asText());
                food.setProtein(foodNode.path("protein").asText());
                
                foodList.add(food);
            }
          
            return foodList;
            
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); 
        }
    }
}
