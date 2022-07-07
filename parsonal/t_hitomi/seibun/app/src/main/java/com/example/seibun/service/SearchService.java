package com.example.seibun.service;

import java.util.List;

import com.example.seibun.entity.IngredientEntity;
import com.example.seibun.mapper.IngredientMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    private IngredientMapper ingredientMapper;

    public List<IngredientEntity> orderList(String sortList,String sort) {
        return ingredientMapper.orderList(sortList,sort);
    }

    public List<IngredientEntity> orderSearchList(String search,String sortList,String sort) {
        return ingredientMapper.orderSearchList(search,sortList,sort);
    }

}
