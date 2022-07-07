package com.example.pokemon.service;

import java.util.List;

import com.example.pokemon.entity.TrainerMasterEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerNameService {

    @Autowired
    TrainerService trainerService;
    
    // t_idから名前をもってくる
    public String trainerName(Integer tId) {

        String name = "";
        List<TrainerMasterEntity> trainerMasterList = trainerService.selectTrainerMaster();

        for(Integer i = 0; i < trainerMasterList.size(); i++) {
            if(tId.equals(trainerMasterList.get(i).getT_id())) {
                name = trainerMasterList.get(i).getName();
            }
        }

        return name;
    }
}
