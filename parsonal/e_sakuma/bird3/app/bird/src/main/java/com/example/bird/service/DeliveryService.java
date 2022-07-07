package com.example.bird.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bird.entity.BirdEntity;
import com.example.bird.mapper.BirdMapper;

@Service
public class DeliveryService {
@Autowired
    private BirdMapper birdMapper;
    
    public List<BirdEntity> getAllList(){
        return birdMapper.selectAll();
    }

    public List<BirdEntity> getSearchList(String species){
        return birdMapper.searchBird(species);
    }

    /*public List<BirdEntity> getSearchList(TbirdEntity species){
        return birdMapper.searchBird(species);
    }*/


    
    
}
