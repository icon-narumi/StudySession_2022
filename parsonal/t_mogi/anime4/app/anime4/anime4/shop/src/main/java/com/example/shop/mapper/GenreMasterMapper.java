package com.example.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.shop.entity.GenreMasterEntity;

@Mapper
public interface GenreMasterMapper {

    @Select("select * from genremaster ")
    List<GenreMasterEntity> selectAll();
}
