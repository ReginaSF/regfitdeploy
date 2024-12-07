package com.example.demo.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Exercise {

    private String category; 
    private String equipment; 
    private String force;  
    @Id
    private String id; 
    private List<String> images; 
    private List<String> instructions;  
    private String level;  
    private String mechanic;
    private String name;  
    private List<String> primaryMuscles;  
    private List<String> secondaryMuscles; 

    public Exercise() {
    }

    public Exercise(String category, String equipment, String force, String id, List<String> images, List<String> instructions, String level, String mechanic, String name, List<String> primaryMuscles, List<String> secondaryMuscles) {
        this.category = category;
        this.equipment = equipment;
        this.force = force;
        this.id = id;
        this.images = images;
        this.instructions = instructions;
        this.level = level;
        this.mechanic = mechanic;
        this.name = name;
        this.primaryMuscles = primaryMuscles;
        this.secondaryMuscles = secondaryMuscles;
    }
    @ManyToMany(mappedBy = "exercises")
    private List<Workout> workouts;

    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMechanic() {
        return mechanic;
    }

    public void setMechanic(String mechanic) {
        this.mechanic = mechanic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPrimaryMuscles() {
        return primaryMuscles;
    }

    public void setPrimaryMuscles(List<String> primaryMuscles) {
        this.primaryMuscles = primaryMuscles;
    }

    public List<String> getSecondaryMuscles() {
        return secondaryMuscles;
    }

    public void setSecondaryMuscles(List<String> secondaryMuscles) {
        this.secondaryMuscles = secondaryMuscles;
    }
}
