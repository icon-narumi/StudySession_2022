package com.example.pokemon.form;

import java.util.List;

import com.example.pokemon.entity.MtrainerEntity;

public class SelectTrainerForm {
    
    List<MtrainerEntity> MtrainerList;
    Integer tId;
    
    public List<MtrainerEntity> getMtrainerList() {
        return MtrainerList;
    }
    public void setMtrainerList(List<MtrainerEntity> mtrainerList) {
        MtrainerList = mtrainerList;
    }
    public Integer gettId() {
        return tId;
    }
    public void settId(Integer tId) {
        this.tId = tId;
    }

    
}
