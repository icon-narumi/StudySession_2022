package com.example.rental2.entity;

public class CustmerEntity {


    //顧客管理データ用Entity
    String custmorName;

    String phoneNumber;

    Integer age;

    Integer gender;

    String address;

    public String getCustmorName() {
        return custmorName;
    }

    public void setCustmorName(String custmorName) {
        this.custmorName = custmorName;
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
