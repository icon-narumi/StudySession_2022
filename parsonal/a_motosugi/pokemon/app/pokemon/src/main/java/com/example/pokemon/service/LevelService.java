package com.example.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokemon.entity.TrainerLevelEntity;

@Service
public class LevelService {

    @Autowired
    TrainerLevelService trainerLevelService;
    
    public Integer trainerLevel(Integer tId) {
        Integer level = 0;

        List<TrainerLevelEntity> list = trainerLevelService.selectTrainerLevel();

        for(Integer i = 0; i < list.size(); i++) {
            // tIdが一致するレベルを取り出す
            if(tId.equals(list.get(i).getT_id())) {
                level = list.get(i).getLevel();
            }
        }
        return  level;
    }
}
