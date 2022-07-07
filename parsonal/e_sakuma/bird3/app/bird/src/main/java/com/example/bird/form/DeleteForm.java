package com.example.bird.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.example.bird.entity.StrBirdEntity;

public class DeleteForm {

    @NotEmpty(message = "種類が入力されていません")
    String species;
   
    String comment;

    List<StrBirdEntity> list;

    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public List<StrBirdEntity> getList() {
        return list;
    }
    public void setList(List<StrBirdEntity> list) {
        this.list = list;
    }
    
}
