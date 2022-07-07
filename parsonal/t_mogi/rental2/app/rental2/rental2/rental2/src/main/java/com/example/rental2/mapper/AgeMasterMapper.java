package com.example.rental2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.rental2.entity.AgeMasterEntity;

@Mapper
public interface AgeMasterMapper {

        @Select("select * from genremaster ")
        List<AgeMasterEntity> selectAll();
    }


