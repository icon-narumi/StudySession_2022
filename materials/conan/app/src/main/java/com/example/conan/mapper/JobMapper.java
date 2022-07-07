package com.example.conan.mapper;

import java.util.List;

import com.example.conan.entity.JobEntity;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface JobMapper {

  @Select("select job_id as jobId, job_name as jobName from m_job order by job_id asc")
  List<JobEntity> selectAll();
}
