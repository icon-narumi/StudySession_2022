package com.example.rental2.mapper.customer;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.rental2.entity.customer.GenderMasterEntity;


@Mapper
public interface GenderMasterMapper {

    @Select("select * from gendermaster ")
    List<GenderMasterEntity> selectAll();
}
