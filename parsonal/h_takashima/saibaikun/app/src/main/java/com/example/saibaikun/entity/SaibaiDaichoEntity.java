package com.example.saibaikun.entity;

public class SaibaiDaichoEntity {

    private Integer saibaiDaichoId;
    private Integer characterId;
    private String  saibaiName;
    private Integer userId;
    private String  level;


    public Integer getSaibaiDaichoId() {
        return saibaiDaichoId;
    }
    public void setSaibaiDaichoId(Integer saibaiDaichoId) {
        this.saibaiDaichoId = saibaiDaichoId;
    }
    public Integer getCharacterId() {
        return characterId;
    }
    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }
    public String getSaibaiName() {
        return saibaiName;
    }
    public void setSaibaiName(String saibaiName) {
        this.saibaiName = saibaiName;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }


}