package com.example.pokemon.service.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokemon.entity.MtrainerEntity;
import com.example.pokemon.mapper.MtrainerMapper;

@Service
public class MtrainerService {
    
    @Autowired
    private MtrainerMapper mtrainerMapper;

    public List<MtrainerEntity> selectMtrainer() {
        return mtrainerMapper.selectMtrainer();
    }


}
