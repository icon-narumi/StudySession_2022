package com.example.pokemon.form;

import java.util.List;

import com.example.pokemon.bean.NameLevelBean;
import com.example.pokemon.entity.TrainerMasterEntity;

public class BattleForm {
    
    List<TrainerMasterEntity> trainerList;
    List<NameLevelBean> trainerLevelList;
    Integer tId1;
    Integer tId2;

    public List<TrainerMasterEntity> getTrainerList() {
        return trainerList;
    }

    public void setTrainerList(List<TrainerMasterEntity> trainerList) {
        this.trainerList = trainerList;
    }

    public List<NameLevelBean> getTrainerLevelList() {
        return trainerLevelList;
    }

    public void setTrainerLevelList(List<NameLevelBean> trainerLevelList) {
        this.trainerLevelList = trainerLevelList;
    }

    public Integer gettId1() {
        return tId1;
    }

    public void settId1(Integer tId1) {
        this.tId1 = tId1;
    }

    public Integer gettId2() {
        return tId2;
    }

    public void settId2(Integer tId2) {
        this.tId2 = tId2;
    }


    
}
