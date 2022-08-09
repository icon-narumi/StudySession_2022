package com.example.pokemon.bean;

// 手持ち表示用
public class ViewPartnerBean implements Cloneable{

    Integer num;
    Integer id;
    String name;
    String type1;
    String type2;
    Integer strength;
    Integer power;
    String attackType;
    
    @Override
    public ViewPartnerBean clone() {
        ViewPartnerBean viewPartnerBean = null;

        try {
            viewPartnerBean = (ViewPartnerBean) super.clone();
        }catch(Exception e) {
            viewPartnerBean = null;
        }
        return viewPartnerBean;
    }
    
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType1() {
        return type1;
    }
    public void setType1(String type1) {
        this.type1 = type1;
    }
    public String getType2() {
        return type2;
    }
    public void setType2(String type2) {
        this.type2 = type2;
    }
    public Integer getStrength() {
        return strength;
    }
    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }
    public Integer getPower() {
        return power;
    }
    public void setPower(Integer power) {
        this.power = power;
    }

}
