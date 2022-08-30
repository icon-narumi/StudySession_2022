package com.example.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokemon.entity.MtrainerEntity;
import com.example.pokemon.service.mapper.MtrainerService;

@Service
public class TrainerService {
    Integer tId;

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }
    
    @Autowired
    MtrainerService mtrainerService;
    
    public String trainerName() {

        String name = "";
        List<MtrainerEntity> mtrainerList = mtrainerService.selectMtrainer();

        for(Integer i = 0; i < mtrainerList.size(); i++) {
            if(tId.equals(mtrainerList.get(i).gettId())) {
                name = mtrainerList.get(i).getName();
            }
        }

        return name;
    }
    
}
