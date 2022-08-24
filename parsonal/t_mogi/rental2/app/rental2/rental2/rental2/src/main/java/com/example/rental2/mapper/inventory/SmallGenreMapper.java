package com.example.rental2.mapper.inventory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.rental2.entity.inventory.SmallGenreEntity;

@Mapper
public interface SmallGenreMapper {

    // *@Select("select * from smallgenre")*/
    List<SmallGenreEntity> selectAll();
}
