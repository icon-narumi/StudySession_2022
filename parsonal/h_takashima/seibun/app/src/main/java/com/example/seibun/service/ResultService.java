package com.example.seibun.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.seibun.entity.IngredientEntity;
// import com.example.seibun.mapper.IngredientMapper;

@Service
public class ResultService {
    
    @Autowired
    SearchService searchService;

    public List<IngredientEntity> getResultList(String search,String sortList,String sort) {
        if( search.isEmpty() ) {
        //検索ワードがない
            return searchService.orderList(sortList,sort);
        } else {
        //検索ワードがある
            return searchService.orderSearchList(search,sortList,sort);
        }
    }


    @Autowired
    private EditService editService;
    public IngredientEntity getSelectList(String selectName) {
        return editService.editData(selectName);
    }

    // public boolean editEntity(String category,String name,String color,BigDecimal calorie,BigDecimal protein) {
    //     return true;
    // }

}
