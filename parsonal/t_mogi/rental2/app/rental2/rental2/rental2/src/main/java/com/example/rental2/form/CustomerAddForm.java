package com.example.rental2.form;

import java.util.List;

import com.example.rental2.bean.CustomerSelectBean;

public class CustomerAddForm {

    private String customerName;

    private String phoneNumber;

    private Integer age;

    private Integer gender;

    private String address;

    private List<CustomerSelectBean> list;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

}
