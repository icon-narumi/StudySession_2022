package com.example.conan.controller;

import com.example.conan.form.InputForm;
import com.example.conan.entity.CharacterEntity;
import com.example.conan.form.IndexForm;
import com.example.conan.service.ConanRegistService;
import com.example.conan.service.ConanSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

  @Autowired
  ConanSearchService conanSearchService;

  @Autowired
  ConanRegistService conanRegistService;

  // 初期表示
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String init(Model model) {

    IndexForm indexForm = new IndexForm();

    String keywordName = "";
    indexForm.setKeywordName(keywordName);
    indexForm.setDisplayList(conanSearchService.getDisplayData(keywordName));
    indexForm.setResultMessage("");

    model.addAttribute("indexForm", indexForm);

    return "index";
  }

  // 検索ボタン押下時
  @RequestMapping(value = "/submit", params = "search", method = RequestMethod.POST)
  public String searchByName(@ModelAttribute IndexForm indexForm, Model model) {

    // 画面から検索キーワードを取得
    String keywordName = indexForm.getKeywordName();

    // 曖昧検索する
    indexForm.setDisplayList(conanSearchService.getDisplayData(keywordName));

    indexForm.setResultMessage("");

    model.addAttribute("indexForm", indexForm);

    return "index";
  }

  // 追加ボタン押下時
  @RequestMapping(value = "/submit", params = "add", method = RequestMethod.POST)
  public String gotoAdd(Model model) {

    InputForm inputForm = new InputForm();

    inputForm.setName("");
    inputForm.setSexId(null);
    inputForm.setJobId(null);

    inputForm.setSexList(conanSearchService.getSexList());
    inputForm.setJobList(conanSearchService.getJobList());

    model.addAttribute("inputForm", inputForm);

    return "add";
  }

  // 更新ボタン押下時
  @RequestMapping(value = "/submit", params = "update", method = RequestMethod.POST)
  public String gotoUpdate(@RequestParam String update, Model model) {

    InputForm inputForm = new InputForm();

    CharacterEntity characterEntity = conanSearchService.getDisplayOneData(update);

    inputForm.setName(characterEntity.getName());
    inputForm.setSexId(characterEntity.getSexId().toString());
    inputForm.setJobId(characterEntity.getJobId().toString());

    inputForm.setSexList(conanSearchService.getSexList());
    inputForm.setJobList(conanSearchService.getJobList());

    model.addAttribute("inputForm", inputForm);

    return "update";
  }

  // 削除ボタン押下時
  @RequestMapping(value = "/submit", params = "delete", method = RequestMethod.POST)
  public String delete(@RequestParam String delete, @ModelAttribute IndexForm indexForm, Model model) {
    String resultMessage = "正常に登録しました。";

    boolean result = conanRegistService.deleteData(delete);

    if (!result) {
      resultMessage = "登録できませんでした。";
    }

    String name = indexForm.getKeywordName();
    indexForm.setDisplayList(conanSearchService.getDisplayData(name));
    indexForm.setResultMessage(resultMessage);

    // VeiwにFormをセットする
    model.addAttribute("indexForm", indexForm);

    // shop.htmlを表示する
    return "index";
  }
}