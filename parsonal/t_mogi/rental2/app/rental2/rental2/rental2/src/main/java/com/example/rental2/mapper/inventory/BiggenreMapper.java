package com.example.rental2.mapper.inventory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.rental2.entity.inventory.BigGenreEntity;

@Mapper
public interface BiggenreMapper{
    
///*  @Select("select * from biggenre")*/
List<BigGenreEntity> selectAll();

}
