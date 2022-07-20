package com.example.saibaikun.form;

import java.util.List;

import com.example.saibaikun.entity.CharacterEntity;

public class SetupForm2 extends SetupForm {

    private Integer characterId;
    private Integer characterName;
    private String  imgPath;

    private String  userCheck;
    private Integer userId;

    private List<CharacterEntity> characterList;

    public Integer getCharacterId() {
        return characterId;
    }
    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }
    public Integer getCharacterName() {
        return characterName;
    }
    public void setCharacterName(Integer characterName) {
        this.characterName = characterName;
    }
    public List<CharacterEntity> getCharacterList() {
        return characterList;
    }
    public void setCharacterList(List<CharacterEntity> characterList) {
        this.characterList = characterList;
    }
    public String getImgPath() {
        return imgPath;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    public String getUserCheck() {
        return userCheck;
    }
    public void setUserCheck(String userCheck) {
        this.userCheck = userCheck;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
