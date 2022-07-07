package com.example.rental2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rental2.entity.AgeMasterEntity;
import com.example.rental2.entity.CustmerEntity;
import com.example.rental2.mapper.AgeMasterMapper;
import com.example.rental2.mapper.CustmorManagementMapper;
import com.example.rental2.mapper.GenderMasterMapper;

@Service
public class CustomerRegistService {

    @Autowired
    private CustmorManagementMapper custmorManagementMapper;
    @Autowired
    private AgeMasterMapper ageMasterMapper;
    @Autowired
    private GenderMasterMapper genderMasterMapper;

    // 顧客データの登録更新削除するサービス

    // 顧客データをテーブルより参照
    public List<CustmerEntity> selectAll() {
        return custmorManagementMapper.selectAll();
    }

    // カスタマーマネージメントテーブルにinsert処理
    public void insertByCustomer(String customerName,String phoneNumber,Integer age,Integer gender,String address) {
        custmorManagementMapper.insertByCustmerInformation(customerName,phoneNumber,age,gender,address);
    }

    public List<AgeMasterEntity> selectAgeAll(){

        List<AgeMasterEntity> ageList;

        ageList = ageMasterMapper.selectAll();
        return ageList;

    }








}
