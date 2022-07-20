package com.example.saibaikun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.saibaikun.entity.CharacterEntity;
import com.example.saibaikun.entity.SaibaiDaichoEntity;
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
    public Integer userCheck(String userName) {
        System.out.println("★"+userName);

        return userMapper.userCheck(userName);
    }

    //レコード追加準備 ユーザーID（シーケンス）を取得
    public Integer getUserId() {
        return userMapper.getUserId();
    }

    //ユーザー情報登録（新規）
    public boolean addUserData(UserEntity userEntity) {
        try {
            userMapper.userAddEntity(userEntity);
            System.out.println("かいぬし登録成功だよ");

        } catch (Exception e) {
            System.out.println("かいぬし登録失敗-------------------str");
            System.out.println(e);
            System.out.println("かいぬし登録失敗-------------------end");
            return false;
        }
        return true;
    }


    //レコード追加準備 ユーザーID（シーケンス）を取得
    public Integer getDaichoId() {
        return saibaikunMapper.getDaichoId();
    }

    //さいばい台帳登録（新規）
    public boolean addSaibaiData(SaibaiDaichoEntity saibaiDaichoEntity) {
        try {
            saibaikunMapper.saibaiAddEntity(saibaiDaichoEntity);
            System.out.println("さいばい台帳登録成功だよ");

        } catch (Exception e) {
            System.out.println("さいばい台帳登録失敗-------------------str");
            System.out.println(e);
            System.out.println("さいばい台帳登録失敗-------------------end");

            return false;
        }
        return true;
    }

}
