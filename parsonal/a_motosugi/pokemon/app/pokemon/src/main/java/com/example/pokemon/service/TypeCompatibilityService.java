package com.example.pokemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokemon.mapper.TypeCompatibilityMapper;

@Service
public class TypeCompatibilityService {

    @Autowired
    TypeCompatibilityMapper typeCompatibilityMapper;

    // effectをとってくる
    public double selectTypeCompatibilityEffect(String attackType, String defenceType) {
        return typeCompatibilityMapper.selectTypeCompatibilityEffect(attackType, defenceType);
    }
    
}
