package com.example.saibaikun.bean;

import com.example.saibaikun.entity.CharacterEntity;

public class GetLoginInfoBean extends CharacterEntity {

    String   saibaiName;
    Integer  saibaiDaichoId;

    public String getSaibaiName() {
        return saibaiName;
    }
    public void setSaibaiName(String saibaiName) {
        this.saibaiName = saibaiName;
    }
    public Integer getSaibaiDaichoId() {
        return saibaiDaichoId;
    }
    public void setSaibaiDaichoId(Integer saibaiDaichoId) {
        this.saibaiDaichoId = saibaiDaichoId;
    }

}
