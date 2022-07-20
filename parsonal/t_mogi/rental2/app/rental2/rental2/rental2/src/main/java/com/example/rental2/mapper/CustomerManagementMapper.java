package com.example.rental2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.rental2.bean.CustomerSelectBean;
import com.example.rental2.entity.CustmerEntity;

@Mapper
public interface CustomerManagementMapper {

        // 顧客管理上のデータをsqlで操作
        //データを全て取り出し。
        @Select("select * from customermanagement")
        List<CustmerEntity> selectAll();
        
        //データをテーブル結合で取り出し。
        @Select("select c.customername,c.phonenumber,a.age,g.gender,c.address,c.id "
                        + "from customermanagement c "
                        + "left join agemaster a on c.age = a.ageid "
                        + "left join gendermaster g on c.gender = g.genderid")
        List<CustomerSelectBean> selectByCustomerInformation();

        //追加処理（idをシーケンスで採番）
        @Insert("INSERT INTO customermanagement (id,customerName,phoneNumber,age,gender,address) "
        +"VALUES(nextval('customer_id_seq'),#{customerName}, #{phoneNumber},#{age},#{gender},#{address})")
        void insertByCustomerInformation(
                        @Param("customerName") String customerName, @Param("phoneNumber") String phoneNumber,
                        @Param("age") Integer age, @Param("gender") Integer gender,
                        @Param("address") String address);

        @Delete("delete from customermanagement where id = #{id}")
        void deleteByCustomerInformation(@Param("id") Integer id);


        @Update("UPDATE users SET name = #{name} WHERE id = #{id}")
        void UpdateBycustomerInformation(

        );
}
