package com.example.seibun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.seibun.entity.IngredientEntity;
import com.example.seibun.mapper.IngredientMapper;

@Service
public class AddService {

    @Autowired
    private IngredientMapper ingredientMapper;

    public boolean addData(IngredientEntity ingredientEntity) {
        try {
            ingredientMapper.addEntity(ingredientEntity);
        } catch (Exception e) {
            System.out.println("addろぐ："+e);
            return false;
        }
        return true;
    }

}
