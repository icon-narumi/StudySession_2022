package com.example.conan.form;

import java.util.List;

import com.example.conan.bean.DisplayBean;

public class IndexForm {

  private String keywordName;

  private List<DisplayBean> displayList;

  private String resultMessage;

  public String getKeywordName() {
    return keywordName;
  }

  public void setKeywordName(String keywordName) {
    this.keywordName = keywordName;
  }

  public List<DisplayBean> getDisplayList() {
    return displayList;
  }

  public void setDisplayList(List<DisplayBean> displayList) {
    this.displayList = displayList;
  }

  public String getResultMessage() {
    return resultMessage;
  }

  public void setResultMessage(String resultMessage) {
    this.resultMessage = resultMessage;
  }

}
