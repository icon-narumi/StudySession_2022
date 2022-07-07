package com.example.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokemon.entity.TrainerLevelEntity;
import com.example.pokemon.mapper.TrainerLevelMapper;

@Service
public class TrainerLevelService {
    
    @Autowired
    TrainerLevelMapper trainerLevelMapper;

    public List<TrainerLevelEntity> selectTrainerLevel() {
        return trainerLevelMapper.selectTrainerLevel();
    }

    public void updateLevel(Integer trainerLevel, Integer tId) {
        trainerLevelMapper.updateLevel(trainerLevel, tId);
    }
}
