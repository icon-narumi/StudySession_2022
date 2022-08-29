package com.example.rental2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rental2.bean.CustomerSelectBean;
import com.example.rental2.entity.customer.AgeMasterEntity;
import com.example.rental2.entity.customer.CustomerEntity;
import com.example.rental2.entity.customer.GenderMasterEntity;
import com.example.rental2.mapper.customer.AgeMasterMapper;
import com.example.rental2.mapper.customer.CustomerManagementMapper;
import com.example.rental2.mapper.customer.GenderMasterMapper;

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
    public List<CustomerEntity> selectAll() {
        return customerManagementMapper.selectAll();
    }

    public List<CustomerSelectBean> selectByCustomerInformation() {
        return customerManagementMapper.selectByCustomerInformation();
    }

    // カスタマーマネージメントテーブルにinsert処理
    public void insertByCustomer(String customerName, String phoneNumber, Integer ageId, Integer gender,
            String address) {
        customerManagementMapper.insertByCustomerInformation(customerName, phoneNumber, ageId, gender, address);
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

    // 削除処理をmapperに命令する。
    public void deleteByCustomerInformation(Integer id) {
        customerManagementMapper.deleteByCustomerInformation(id);
    }

    // 更新処理をmapperに命令する。
    public void updateBycustomerInformation(String customerName, String phoneNumber, Integer ageId, Integer gender,
            String address, Integer id) {
        customerManagementMapper.updateByCustomerInformation(customerName, phoneNumber, ageId, gender, address, id);
    }

    // 更新データのみをselect文で抽出
    // メソッド動かしたときの型（今回はCustomerEntity）があっていればなんでも変数はOK
    public CustomerEntity updateSelectBycustomerInformation(Integer id) {
        CustomerEntity listResult = customerManagementMapper.selectByCustomerId(id);
        return listResult;
    }
}
