package com.example.pokemon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.pokemon.entity.TypeEntity;

@Mapper
public interface MTypeMapper {

    @Select("select type_id as typeId, type from m_type")
    List<TypeEntity> selectAllType();

    @Select("select type from m_type where type_id = #{typeId}")
    String selectType(@Param("typeId") Integer typeId);
    
}
