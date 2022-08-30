package com.example.pokemon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.pokemon.entity.MtrainerEntity;

@Mapper
public interface MtrainerMapper {

    // トレーナー
    List<MtrainerEntity> selectMtrainer();

    
}