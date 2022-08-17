package com.example.rental2.form.customer;

import java.util.List;

import com.example.rental2.bean.CustomerSelectBean;
import com.example.rental2.entity.customer.AgeMasterEntity;
import com.example.rental2.entity.customer.GenderMasterEntity;

public class CustomerAddForm {

    private String customerName;

    private String phoneNumber;

    private Integer ageId;

    private Integer gender;

    private String address;

    private List<CustomerSelectBean> list;

    private List<AgeMasterEntity> agelist; 

    private List<GenderMasterEntity> genderlist;

    public List<CustomerSelectBean> getList() {
        return list;
    }

    public void setList(List<CustomerSelectBean> list) {
        this.list = list;
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

    public Integer getAgeId() {
        return ageId;
    }

    public void setAgeId(Integer ageId) {
        this.ageId = ageId;
    }

    

}
