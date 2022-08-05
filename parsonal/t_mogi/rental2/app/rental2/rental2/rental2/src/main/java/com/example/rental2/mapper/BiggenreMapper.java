package com.example.rental2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.rental2.entity.BigGenreEntity;

@Mapper
public interface BiggenreMapper{
    
@Select("select * from biggenre")
List<BigGenreEntity> selectAll();

}
