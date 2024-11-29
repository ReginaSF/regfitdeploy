package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.FoodMacros;
import com.example.demo.Service.Activity.FoodMacrosService;

@Controller
public class FoodMacrosController {

    private final FoodMacrosService foodMacrosService;

    @Autowired
    public FoodMacrosController(FoodMacrosService foodMacrosService) {
        this.foodMacrosService = foodMacrosService;
    }

    @GetMapping("/foodmacros")
    public String getFoodMacros(@RequestParam(value = "query", required = false) String query, Model model) {
       
        if (query != null && !query.isEmpty()) {
            List<FoodMacros> foodList = foodMacrosService.getNutritionData(query);  
            model.addAttribute("foodList", foodList);  
        } else {
            model.addAttribute("foodList", null);  
        }

        return "food";  
    }
}
