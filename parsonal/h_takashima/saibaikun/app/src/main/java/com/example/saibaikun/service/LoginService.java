package com.example.saibaikun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.saibaikun.bean.GetLoginInfoBean;
import com.example.saibaikun.bean.SaibaikunStatusBean;
import com.example.saibaikun.mapper.SaibaikunMapper;
import com.example.saibaikun.mapper.StatusMapper;


@Service
public class LoginService {

    @Autowired
    private SaibaikunMapper saibaikunMapper;
    
    @Autowired
    private StatusMapper statusMapper;
    // private UserMapper userMapper;


    //キャラクターリスト取得（ログイン）
    public List<GetLoginInfoBean> getUserCharacterList(Integer userId) {
        return saibaikunMapper.selectSaibaikun(userId);
    }

    public SaibaikunStatusBean getSaibaiStatus(Integer saibaiDaichoId,String date) {
        return statusMapper.getStatus(saibaiDaichoId,date);
    }


    // //ログインログ追加
    // public boolean addSaibaiData(LoginLogEntity loginLogEntity) {
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

}
