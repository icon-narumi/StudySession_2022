package com.example.conan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.conan.bean.DisplayBean;
import com.example.conan.entity.CharacterEntity;
import com.example.conan.entity.JobEntity;
import com.example.conan.entity.SexEntity;
import com.example.conan.mapper.CharacterMapper;
import com.example.conan.mapper.JobMapper;
import com.example.conan.mapper.SexMapper;

@Service
public class ConanSearchService {

  @Autowired
  private CharacterMapper characterMapper;

  @Autowired
  private SexMapper sexMapper;

  @Autowired
  private JobMapper jobMapper;

  // キャラクターテーブルの曖昧検索
  public List<DisplayBean> getDisplayData(String name) {

    return characterMapper.selectLikeName(name);
  }

  // キャラクターテーブルの一意検索
  public CharacterEntity getDisplayOneData(String name) {

    return characterMapper.selectByName(name);
  }

  // 性別マスタリスト取得
  public List<SexEntity> getSexList() {

    return sexMapper.selectAll();
  }

  // 職業マスタリスト取得
  public List<JobEntity> getJobList() {

    return jobMapper.selectAll();
  }
}
