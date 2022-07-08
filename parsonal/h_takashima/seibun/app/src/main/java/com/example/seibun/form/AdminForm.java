package com.example.seibun.form;

import java.util.List;

import com.example.seibun.entity.IngredientEntity;

public class AdminForm {

    private String selectCategory;
    private String selectName;

    private String resultMessage;

    private IngredientEntity selectList;
    private List<IngredientEntity> list;


    public String getSelectCategory() {
        return selectCategory;
    }
    public void setSelectCategory(String selectCategory) {
        this.selectCategory = selectCategory;
    }
    public String getSelectName() {
        return selectName;
    }
    public void setSelectName(String selectName) {
        this.selectName = selectName;
    }
    public String getResultMessage() {
        return resultMessage;
    }
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
    public IngredientEntity getSelectList() {
        return selectList;
    }
    public void setSelectList(IngredientEntity selectList) {
        this.selectList = selectList;
    }

    public List<IngredientEntity> getList() {
        return list;
    }
    public void setList(List<IngredientEntity> list) {
        this.list = list;
    }


    

}
