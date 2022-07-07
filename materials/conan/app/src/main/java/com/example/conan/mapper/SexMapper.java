package com.example.conan.mapper;

import java.util.List;

import com.example.conan.entity.SexEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SexMapper {

  @Select("select sex_id as sexId, sex_name as sexName from m_sex order by sex_id asc")
  List<SexEntity> selectAll();
}
