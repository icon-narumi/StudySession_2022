package com.example.seibun.entity;

import java.math.BigDecimal;

public class IngredientEntity {

    private String category;
    private String name;
    private String color;
    private BigDecimal calorie;
    private BigDecimal protein;
    
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public BigDecimal getCalorie() {
        return calorie;
    }
    public void setCalorie(BigDecimal calorie) {
        this.calorie = calorie;
    }
    public BigDecimal getProtein() {
        return protein;
    }
    public void setProtein(BigDecimal protein) {
        this.protein = protein;
    }
  

    
}
