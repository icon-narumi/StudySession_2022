package com.example.petbird.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.petbird.entity_master.ColorEntity;
import com.example.petbird.entity_master.SexEntity;
import com.example.petbird.entity_master.SpeciesEntity;

@Mapper
public interface M_MasterMapper {

    @Select("select * from m_species")                   
    List<SpeciesEntity> speciesAll();

    @Select("select * from m_sex")                
    List<SexEntity> sexAll();

    @Select("select * from m_color")                   
    List<ColorEntity> colorAll();


    
}
