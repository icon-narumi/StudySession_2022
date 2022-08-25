package com.example.petbird.form_client;

import java.util.List;

import com.example.petbird.bean.StrPetBirdBean;
public class SearchForm {
    
    Integer[] id;
    Integer species;
    Integer sex;
    Integer color;
    Integer price;
    Integer count;
    String  total;

    String comment;

    List<StrPetBirdBean> strPetBirdBeanList;

    public Integer[] getId() {
        return id;
    }

    public void setId(Integer[] id) {
        this.id = id;
    }

    public Integer getSpecies() {
        return species;
    }

    public void setSpecies(Integer species) {
        this.species = species;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<StrPetBirdBean> getStrPetBirdBeanList() {
        return strPetBirdBeanList;
    }

    public void setStrPetBirdBeanList(List<StrPetBirdBean> strPetBirdBeanList) {
        this.strPetBirdBeanList = strPetBirdBeanList;
    }

   


   

}
