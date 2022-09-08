package com.example.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pokemon.entity.TypeEntity;
import com.example.pokemon.mapper.MTypeMapper;

@Service
public class TypeService {

    @Autowired
    MTypeMapper mTypeMapper;

    public List<TypeEntity> selectTypeList() {
        return mTypeMapper.selectAllType();
    }

    public String selectType(Integer typeId) {
        return mTypeMapper.selectType(typeId);
    }
    
}
