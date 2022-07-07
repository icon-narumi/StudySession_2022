package com.example.conan.form;

import java.util.List;

import com.example.conan.entity.JobEntity;
import com.example.conan.entity.SexEntity;

public class InputForm {

  private String name;

  private String sexId;

  private String jobId;

  private List<SexEntity> sexList;

  private List<JobEntity> jobList;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSexId() {
    return sexId;
  }

  public void setSexId(String sexId) {
    this.sexId = sexId;
  }

  public String getJobId() {
    return jobId;
  }

  public void setJobId(String jobId) {
    this.jobId = jobId;
  }

  public List<SexEntity> getSexList() {
    return sexList;
  }

  public void setSexList(List<SexEntity> sexList) {
    this.sexList = sexList;
  }

  public List<JobEntity> getJobList() {
    return jobList;
  }

  public void setJobList(List<JobEntity> jobList) {
    this.jobList = jobList;
  }

}
