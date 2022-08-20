package com.example.pokemon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.pokemon.entity.MtrainerEntity;

@Mapper
public interface MtrainerMapper {

    @Select("select t_id as tId, name from m_trainer order by t_id ASC")
    List<MtrainerEntity> selectMtrainer();
    
}