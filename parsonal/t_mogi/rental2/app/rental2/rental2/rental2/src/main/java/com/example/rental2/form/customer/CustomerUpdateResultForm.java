package com.example.rental2.form.customer;

import java.util.List;

import com.example.rental2.entity.customer.AgeMasterEntity;
import com.example.rental2.entity.customer.GenderMasterEntity;

public class CustomerUpdateResultForm {
    

//顧客マスタのデータ（データベース上の）
    private String customerName;

    private String phoneNumber;

    private Integer ageId;

    private Integer gender;

    private String address;

    public Integer id;


  //セレクトボックスの中身をつくるためのリスト
    private List<AgeMasterEntity> agelist; 

    private List<GenderMasterEntity> genderlist;

    
    

    public List<AgeMasterEntity> getAgelist() {
        return agelist;
    }

    public void setAgelist(List<AgeMasterEntity> agelist) {
        this.agelist = agelist;
    }

    public List<GenderMasterEntity> getGenderlist() {
        return genderlist;
    }

    public void setGenderlist(List<GenderMasterEntity> genderlist) {
        this.genderlist = genderlist;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgeId() {
        return ageId;
    }

    public void setAgeId(Integer ageId) {
        this.ageId = ageId;
    }

    
}
