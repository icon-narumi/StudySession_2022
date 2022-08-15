package com.example.rental2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.rental2.entity.SmallGenreEntity;

@Mapper
public interface SmallGenreMapper {
    
    @Select("select * from smallgenre")
    List<SmallGenreEntity> selectAll();
}