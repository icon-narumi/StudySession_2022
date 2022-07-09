package com.example.seibun.form;

import java.math.BigDecimal;

import com.example.seibun.entity.IngredientEntity;

public class ConfirmForm {

    private String Pcategory;
    private String Pname;

    private String category;
    private String name;
    private String color;
    private BigDecimal calorie;
    private BigDecimal protein;

    IngredientEntity editEntity;

    public String getPcategory() {
        return Pcategory;
    }

    public void setPcategory(String pcategory) {
        Pcategory = pcategory;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

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

    public IngredientEntity getEditEntity() {
        return editEntity;
    }

    public void setEditEntity(IngredientEntity editEntity) {
        this.editEntity = editEntity;
    }
 
    
}
