package com.example.rental2.mapper.inventory;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.rental2.entity.inventory.StatusEntity;

@Mapper
public interface StatusMapper {
    

    @Select("select * from status")
    List <StatusEntity> selectAll();

}
