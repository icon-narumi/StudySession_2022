package com.example.rental2.mapper.customer;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.rental2.bean.CustomerSelectBean;
import com.example.rental2.entity.customer.CustomerEntity;

@Mapper
public interface CustomerManagementMapper {

        // 顧客管理上のデータをsqlで操作
        // データを全て取り出し。
        @Select("select * from customermanagement")
        List<CustomerEntity> selectAll();

        // データをテーブル結合で取り出し。
        @Select("select c.customername,c.phonenumber,a.age,g.gender,c.address,c.id "
                        + "from customermanagement c "
                        + "left join agemaster a on c.ageid = a.ageid "
                        + "left join gendermaster g on c.genderid = g.genderid")
        List<CustomerSelectBean> selectByCustomerInformation();

        // 更新するデータのみを取り出す（1行）
        @Select("select customername,phonenumber,ageid,genderid,address,id "
                        + "from customermanagement c "
                        + "where id = #{id}")
        CustomerEntity selectByCustomerId(
                        @Param("id") Integer id);

        // 追加処理（idをシーケンスで採番）
        @Insert("INSERT INTO customermanagement (id,customerName,phoneNumber,ageid,genderid,address) "
                        + "VALUES(nextval('customer_id_seq'),#{customerName}, #{phoneNumber},#{ageid},#{genderid},#{address})")
        void insertByCustomerInformation(
                        @Param("customerName") String customerName, @Param("phoneNumber") String phoneNumber,
                        @Param("ageid") Integer ageId, @Param("genderid") Integer gender,
                        @Param("address") String address);

        @Delete("delete from customermanagement where id = #{id}")
        void deleteByCustomerInformation(@Param("id") Integer id);

        @Update("update customermanagement set customerName =#{customerName},phoneNumber =#{phoneNumber},ageid =#{ageid},genderid =#{genderid},address =#{address} "
                        + "where  id =#{id}")
        void updateByCustomerInformation(
                        @Param("customerName") String customerName, @Param("phoneNumber") String phoneNumber,
                        @Param("ageid") Integer ageId, @Param("genderid") Integer gender,
                        @Param("address") String address, @Param("id") Integer id);

}
