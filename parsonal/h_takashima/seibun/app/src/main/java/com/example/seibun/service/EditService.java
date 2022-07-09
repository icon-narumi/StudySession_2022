package com.example.seibun.service;

// import java.math.BigDecimal;

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


    public boolean editaddData(IngredientEntity ingredientEntity,String Pcat,String Pname) {
        try {
            ingredientMapper.editEntity(ingredientEntity,Pcat,Pname);
            System.out.println("edit："+Pname);
            System.out.println("edittt："+ingredientEntity.getName());

        } catch (Exception e) {
            System.out.println("editろぐ："+e);
            return false;
        }
        return true;
    }

    public boolean deleteData(String selectName) {
        try {
            ingredientMapper.deleteData(selectName);
            System.out.println("ediiiit："+selectName);
        } catch (Exception e) {
            System.out.println("deleteろぐ："+e);
            return false;
        }
        return true;
    }

}
