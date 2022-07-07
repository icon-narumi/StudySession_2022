package com.example.conan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.conan.entity.CharacterEntity;
import com.example.conan.mapper.CharacterMapper;

@Service
public class ConanRegistService {

  @Autowired
  private CharacterMapper characterMapper;

  // キャラクターテーブルの追加登録
  public boolean addData(CharacterEntity characterEntity) {
    try {
      characterMapper.insertCharacter(characterEntity);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  // キャラクターテーブルの更新
  public boolean updateData(CharacterEntity characterEntity) {

    try {
      characterMapper.updateCharacter(characterEntity);
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    return true;
  }

  // キャラクターテーブルの削除
  public boolean deleteData(String name) {

    try {
      characterMapper.deleteCharacter(name);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

}
