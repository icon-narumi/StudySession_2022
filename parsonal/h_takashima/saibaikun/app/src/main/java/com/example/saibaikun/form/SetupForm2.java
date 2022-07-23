package com.example.saibaikun.form;

import java.util.List;

import com.example.saibaikun.bean.GetLoginInfoBean;

public class SetupForm2 extends SetupForm {

    private Integer characterId;
    private Integer userId;
    private Integer saibaiDaichoId;

    private List<GetLoginInfoBean> characterList;
    // private List<CharacterEntity> characterList;


    public Integer getCharacterId() {
        return characterId;
    }
    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public List<GetLoginInfoBean> getCharacterList() {
        return characterList;
    }
    public void setCharacterList(List<GetLoginInfoBean> characterList) {
        this.characterList = characterList;
    }
    public Integer getSaibaiDaichoId() {
        return saibaiDaichoId;
    }
    public void setSaibaiDaichoId(Integer saibaiDaichoId) {
        this.saibaiDaichoId = saibaiDaichoId;
    }

}
