package com.example.rental2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rental2.bean.CustomerSelectBean;
import com.example.rental2.entity.AgeMasterEntity;
import com.example.rental2.entity.CustmerEntity;
import com.example.rental2.entity.GenderMasterEntity;
import com.example.rental2.mapper.AgeMasterMapper;
import com.example.rental2.mapper.CustomerManagementMapper;
import com.example.rental2.mapper.GenderMasterMapper;

@Service
public class CustomerRegistService {

    @Autowired
    private CustomerManagementMapper customerManagementMapper;
    @Autowired
    private AgeMasterMapper ageMasterMapper;
    @Autowired
    private GenderMasterMapper genderMasterMapper;

    // 顧客データの登録更新削除するサービス

    // 顧客データをテーブルより参照
    public List<CustmerEntity> selectAll() {
        return customerManagementMapper.selectAll();
    }

    public List<CustomerSelectBean> selectByCustomerInformation() {
        return customerManagementMapper.selectByCustomerInformation();
    }

    // カスタマーマネージメントテーブルにinsert処理
    public void insertByCustomer(String customerName, String phoneNumber, Integer age, Integer gender, String address) {
        customerManagementMapper.insertByCustomerInformation(customerName, phoneNumber, age, gender, address);
    }

    public List<AgeMasterEntity> selectAgeAll() {

        List<AgeMasterEntity> ageList;

        ageList = ageMasterMapper.selectAll();
        return ageList;

    }

    public List<GenderMasterEntity> selectGenderAll() {

        List<GenderMasterEntity> genderList;

        genderList = genderMasterMapper.selectAll();
        return genderList;

    }

    public void deleteByCustomerInformation(Integer id){
        customerManagementMapper.deleteByCustomerInformation(id);
    }
}
