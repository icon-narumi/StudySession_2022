package com.example.seibun.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.seibun.entity.IngredientEntity;
import com.example.seibun.mapper.IngredientMapper;

@Service
public class EditService {

    @Autowired
    private IngredientMapper ingredientMapper;

    public IngredientEntity editData(String selectName) {
        return ingredientMapper.editData(selectName);
    }

    public boolean addData(String category,String name,String color,BigDecimal calorie,BigDecimal protein) {
        try {
            ingredientMapper.ingredientEntity(category,name,color,calorie,protein);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
