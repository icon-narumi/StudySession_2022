package com.example.saibaikun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.saibaikun.bean.GetLoginInfoBean;
import com.example.saibaikun.entity.ActionRrkEntity;
import com.example.saibaikun.entity.LoginLogEntity;
import com.example.saibaikun.entity.SaibaiDaichoEntity;
import com.example.saibaikun.entity.UserEntity;
import com.example.saibaikun.mapper.ActionRrkMapper;
import com.example.saibaikun.mapper.LoginLogMapper;
import com.example.saibaikun.mapper.SaibaikunMapper;
import com.example.saibaikun.mapper.UserMapper;


@Service
public class SetupService {

    @Autowired
    private SaibaikunMapper saibaikunMapper;
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ActionRrkMapper actionRrkMapper;

    @Autowired
    private LoginLogMapper loginLogMapper;

    //ユーザー情報があるか確認（新規か既存か判定）
    public Integer userCheck(String userName) {
        return userMapper.userCheck(userName);
    }
    public Integer userCheck2(String userName) {
        return userMapper.userCheck2(userName);
    }

    //キャラクターリスト取得（新規）
    public List<GetLoginInfoBean> getCharacterList() {
        return saibaikunMapper.selectAll();
    }

    //レコード追加準備 ユーザーID（シーケンス）を取得
    public Integer getUserId() {
        return userMapper.getUserId();
    }

    //レコード追加準備 さいばい台帳ID（シーケンス）を取得
    public Integer getDaichoId() {
        return saibaikunMapper.getDaichoId();
    }



	public boolean execute(UserEntity userEntity,SaibaiDaichoEntity saibaiDaichoEntity,LoginLogEntity loginLogEntity,ActionRrkEntity actionRrkEntity) 
    {
        try {
            userMapper.userAddEntity(userEntity);
            System.out.println("かいぬし登録成功だよ");

        } catch (Exception e) {
            System.out.println("かいぬし登録失敗-------------------str");
            System.out.println(e);
            System.out.println("かいぬし登録失敗-------------------end");
            return false;
        }

        try {
            saibaikunMapper.saibaiAddEntity(saibaiDaichoEntity);
            System.out.println("さいばい台帳登録成功だよ");

        } catch (Exception e) {
            System.out.println("さいばい台帳登録失敗-------------------str");
            System.out.println(e);
            System.out.println("さいばい台帳登録失敗-------------------end");

            return false;
        }

        try {
            loginLogMapper.loginLogAddEntity(loginLogEntity);
            System.out.println("ログインログ登録成功だよ");

        } catch (Exception e) {
            System.out.println("ログインログ台帳登録失敗-------------------str");
            System.out.println(e);
            System.out.println("ログインログ台帳登録失敗-------------------end");

            return false;
        }

        try {
            actionRrkMapper.actionRrkAddEntity(actionRrkEntity);
            System.out.println("アクション履歴登録成功だよ");

        } catch (Exception e) {
            System.out.println("アクション履歴登録失敗-------------------str");
            System.out.println(e);
            System.out.println("アクション履歴登録失敗-------------------end");

            return false;
        }
        return true;
    }


    // //ユーザー情報登録（新規）
    // public boolean addUserData(UserEntity userEntity) {
    //     try {
    //         userMapper.userAddEntity(userEntity);
    //         System.out.println("かいぬし登録成功だよ");

    //     } catch (Exception e) {
    //         System.out.println("かいぬし登録失敗-------------------str");
    //         System.out.println(e);
    //         System.out.println("かいぬし登録失敗-------------------end");
    //         return false;
    //     }

    //     return true;
    // }

    // //さいばい台帳登録（新規）
    // public boolean addSaibaiData(SaibaiDaichoEntity saibaiDaichoEntity) {
    //     try {
    //         saibaikunMapper.saibaiAddEntity(saibaiDaichoEntity);
    //         System.out.println("さいばい台帳登録成功だよ");

    //     } catch (Exception e) {
    //         System.out.println("さいばい台帳登録失敗-------------------str");
    //         System.out.println(e);
    //         System.out.println("さいばい台帳登録失敗-------------------end");

    //         return false;
    //     }
    //     return true;
    // }

    // //アクション履歴登録（新規）
    // public boolean addActionRrkData(ActionRrkEntity actionRrkEntity) {
    //     try {
    //         actionRrkMapper.actionRrkAddEntity(actionRrkEntity);
    //         System.out.println("アクション履歴登録成功だよ");

    //     } catch (Exception e) {
    //         System.out.println("アクション履歴登録失敗-------------------str");
    //         System.out.println(e);
    //         System.out.println("アクション履歴登録失敗-------------------end");

    //         return false;
    //     }
    //     return true;
    // }

}
