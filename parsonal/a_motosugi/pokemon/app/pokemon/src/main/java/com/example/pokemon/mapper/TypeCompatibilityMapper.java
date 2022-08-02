package com.example.pokemon.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TypeCompatibilityMapper {

    // effectの数字（こうかはばつぐんとか）をとってくる
    @Select("select effect from type_compatibility where attack_type = #{attackType} and defence_type = #{defenceType}")
    double selectTypeCompatibilityEffect(@Param("attackType") String attackType, @Param("defenceType") String defenceType);
    
}