package com.example.rental2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.rental2.entity.GenderMasterEntity;

@Mapper
public interface GenderMasterMapper {
    


    
    @Select("select * from agemaster ")
    List<GenderMasterEntity> selectAll();
}
