package com.example.conan.controller;

import com.example.conan.entity.CharacterEntity;
import com.example.conan.form.InputForm;
import com.example.conan.form.IndexForm;
import com.example.conan.service.ConanRegistService;
import com.example.conan.service.ConanSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UpdateController {
  @Autowired
  ConanSearchService conanSearchService;

  @Autowired
  ConanRegistService conanRegistService;

  // 登録ボタン押下時
  @PostMapping("/update")
  public String update(@ModelAttribute InputForm inputForm, Model model) {

    String resultMessage = "正常に登録しました。";

    // 登録データの準備
    CharacterEntity characterEntity = new CharacterEntity();

    if (inputForm.getName().isEmpty()) {
      characterEntity.setName(null);
    } else {
      characterEntity.setName(inputForm.getName());
    }
    characterEntity.setSexId(Integer.parseInt(inputForm.getSexId()));
    characterEntity.setJobId(Integer.parseInt(inputForm.getJobId()));

    // 登録処理実行
    boolean result = conanRegistService.updateData(characterEntity);

    // 登録結果の確認およびエラーメッセージ変更
    if (!result) {
      resultMessage = "登録できませんでした。";
    }

    // 検索画面に戻る
    IndexForm indexForm = new IndexForm();
    String keywordName = "";

    indexForm.setKeywordName(keywordName);
    indexForm.setDisplayList(conanSearchService.getDisplayData(keywordName));
    indexForm.setResultMessage(resultMessage);

    model.addAttribute("indexForm", indexForm);

    return "index";
  }

}