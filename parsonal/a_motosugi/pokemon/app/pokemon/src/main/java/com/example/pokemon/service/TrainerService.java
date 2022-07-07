package com.example.pokemon.service;

import com.example.pokemon.entity.TrainerMasterEntity;
import com.example.pokemon.bean.NameLevelBean;
import com.example.pokemon.mapper.TrainerMasterMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    
    @Autowired
    private TrainerMasterMapper trainerMasterMapper;

    public List<TrainerMasterEntity> selectTrainerMaster() {
        return trainerMasterMapper.selectTrainerMaster();
    }

    public List<NameLevelBean> selectTrainerAndLevel() {
        return trainerMasterMapper.selectTrainerAndLevel();
    }

    public String selectTrainerName(Integer tId) {
        return trainerMasterMapper.selectTrainerName(tId);
    }
}
