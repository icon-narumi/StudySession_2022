package com.example.saibaikun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.saibaikun.entity.CharacterEntity;
import com.example.saibaikun.entity.UserEntity;
import com.example.saibaikun.mapper.SaibaikunMapper;
import com.example.saibaikun.mapper.UserMapper;


@Service
public class SetUpService {

    @Autowired
    private SaibaikunMapper saibaikunMapper;
    //キャラクターリスト取得（新規）
    public List<CharacterEntity> getCharacterList() {
        return saibaikunMapper.selectAll();
    }


    @Autowired
    private UserMapper userMapper;

    //ユーザー情報があるか確認
    public boolean userCheck(String userName) {
        userMapper.userCheck(userName);
        return true;
    }

    //ユーザー情報登録（新規）
    public boolean addData(UserEntity userEntity) {
        try {
            userMapper.userAddEntity(userEntity);
            System.out.println("成功だよ");

        } catch (Exception e) {
            System.out.println("addろぐ："+e);
            return false;
        }
        return true;
    }

}
