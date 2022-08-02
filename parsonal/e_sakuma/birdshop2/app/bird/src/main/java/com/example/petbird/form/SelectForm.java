package com.example.petbird.form;




import java.util.List;

import com.example.petbird.bean.IdCountBean;
import com.example.petbird.bean.PetBirdBean;
import com.example.petbird.entity.CastPetBirdEntity;

public class SelectForm {

    Integer id;
    String  total;   
    Integer count;
    Integer cCount;
    String linecount;

    PetBirdBean bean;

    List<PetBirdBean> beanList;   
    List<IdCountBean> idCountBeanList;

    List<CastPetBirdEntity> check;

    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }
    
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public PetBirdBean getBean() {
        return bean;
    }
    public void setBean(PetBirdBean bean) {
        this.bean = bean;
    }
    public List<PetBirdBean> getBeanList() {
        return beanList;
    }
    public void setBeanList(List<PetBirdBean> beanList) {
        this.beanList = beanList;
    }
    public String getLinecount() {
        return linecount;
    }
    public void setLinecount(String linecount) {
        this.linecount = linecount;
    }
    public List<IdCountBean> getIdCountBeanList() {
        return idCountBeanList;
    }
    public void setIdCountBeanList(List<IdCountBean> idCountBeanList) {
        this.idCountBeanList = idCountBeanList;
    }
    
    public List<CastPetBirdEntity> getCheck() {
        return check;
    }
    public void setCheck(List<CastPetBirdEntity> check) {
        this.check = check;
    }
    public Integer getcCount() {
        return cCount;
    }
    public void setcCount(Integer cCount) {
        this.cCount = cCount;
    }
    

   
}
