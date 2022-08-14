package com.example.saibaikun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.saibaikun.bean.SaibaikunStatusBean;
import com.example.saibaikun.mapper.ActionRrkMapper;


@Service
public class ActionService {

    @Autowired
    private ActionRrkMapper actionRrkMapper;

    //アクション：そうじ
    //max3
    public Integer actionNumCheck(Integer saibaiDaichoId,String date) {
        return actionRrkMapper.numCheck(saibaiDaichoId,date);
    }

    // //ごはん
    // public boolean updateAction1(Integer saibaiDaichoId,Integer actionCount1,String date){
    //     try {
    //         actionRrkMapper.updateAction1(saibaiDaichoId,actionCount1,date);
    //         System.out.println("アクション履歴更新成功");

    //     } catch (Exception e) {
    //         System.out.println("アクション履歴更新失敗-------------------str");
    //         System.out.println(e);
    //         System.out.println("アクション履歴更新失敗-------------------end");

    //         return false;
    //     }
    //     return true;
    // }

    //そうじ
    public boolean updateAction2(Integer saibaiDaichoId,Integer actionCount2,String date){
        try {
            actionRrkMapper.updateAction2(saibaiDaichoId,actionCount2,date);
            System.out.println("アクション履歴更新成功");

        } catch (Exception e) {
            System.out.println("アクション履歴更新失敗-------------------str");
            System.out.println(e);
            System.out.println("アクション履歴更新失敗-------------------end");

            return false;
        }
        return true;
    }

    // //あそび
    // public boolean updateAction3(Integer saibaiDaichoId,Integer actionCount3,String date){
    //     try {
    //         actionRrkMapper.updateAction3(saibaiDaichoId,actionCount3,date);
    //         System.out.println("アクション履歴更新成功");

    //     } catch (Exception e) {
    //         System.out.println("アクション履歴更新失敗-------------------str");
    //         System.out.println(e);
    //         System.out.println("アクション履歴更新失敗-------------------end");

    //         return false;
    //     }
    //     return true;
    // }




}
