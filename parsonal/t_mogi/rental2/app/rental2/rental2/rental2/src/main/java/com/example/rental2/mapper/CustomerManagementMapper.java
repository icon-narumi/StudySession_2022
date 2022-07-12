package com.example.rental2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.rental2.bean.CustomerSelectBean;
import com.example.rental2.entity.CustmerEntity;

@Mapper
public interface CustomerManagementMapper {

        // 顧客管理上のデータをsqlで操作

        @Select("select * from customermanagement")
        List<CustmerEntity> selectAll();

        @Select("select c.customername,c.phonenumber,a.age,g.gender,c.address "
                        + "from customermanagement c "
                        + "left join agemaster a on c.age = a.ageid "
                        + "left join gendermaster g on c.gender = g.genderid")
        List<CustomerSelectBean> selectByCustomerInformation();

        @Insert("INSERT INTO users (id, name) VALUES(#{id}, #{name})")
        void insertByCustomerInformation(
                        @Param("customerName") String customerName, @Param("phoneNumber") String phoneNumber,
                        @Param("age") Integer age, @Param("gender") Integer gender,
                        @Param("address") String address);
}
