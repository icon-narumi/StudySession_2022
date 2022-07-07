package com.example.pokemon.form;

import java.util.List;

import com.example.pokemon.entity.TrainerMasterEntity;

public class SelectTrainerForm {
    
    List<TrainerMasterEntity> trainerList;
    Integer tId;

    public List<TrainerMasterEntity> getTrainerList() {
        return trainerList;
    }
    public void setTrainerList(List<TrainerMasterEntity> trainerList) {
        this.trainerList = trainerList;
    }
    public Integer gettId() {
        return tId;
    }
    public void settId(Integer tId) {
        this.tId = tId;
    }
    
}
