package com.example.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.shop.bean.AnimeSelectBean;
import com.example.shop.entity.AnimeEntity;
import com.example.shop.form.AnimeForm;
import com.example.shop.form.DeleteForm;
import com.example.shop.form.Error2Form;
import com.example.shop.form.InsertForm;
import com.example.shop.form.UpdateForm;
import com.example.shop.service.AnimeService;

@Controller
public class MainController {

  // private final Mapper AnimeMapper;

  @Autowired
  private AnimeService animeService;
  private String Error2Form;

  // 最初の画面は/のアドレスなのでここから初期画面
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String init(Model model) {

    AnimeForm animeForm = new AnimeForm();

    animeForm.setGenrelist(animeService.selectGenreAll());

    animeForm.setResult("");
    model.addAttribute("animeForm", animeForm);

    return "anime";

  }

  // 検索処理
  @PostMapping("/select")
  public String orderExecute(@ModelAttribute AnimeForm animeForm, Model model) {

    // アニメサービスからのデータを、リストに入れる
    // List<AnimeEntity> list = animeService.selectByGenre(animeForm.getGenre());

    // 入力した値と等しいデータをリストに入れる。
    List<AnimeSelectBean> list;

    // ジャンルが空だったかどうか
    if (animeForm.getGenre() == 0) {

      list = animeService.selectByTitle(animeForm.getInput());
    } else {

      list = animeService.selectByAnime(animeForm.getInput(), animeForm.getGenre());

    }

    animeForm.setList(list);
    // ジャンルセレクトボックスを設置
    animeForm.setGenrelist(animeService.selectGenreAll());
    model.addAttribute("animeForm", animeForm);

    // shop.htmlを表示する
    return "anime";
  }

  // 追加処理
  @RequestMapping(value = "/", params = "add", method = RequestMethod.POST)
  public String insertProces(@Validated InsertForm insertForm, BindingResult bindingResult, Model model) {

    // エラーが出たら最初に戻る
    if (bindingResult.hasErrors()) {
      insertForm.setGenrelist(animeService.selectGenreInsert());

      return "insert";
    }

    AnimeForm animeForm = new AnimeForm();
    Error2Form error2form = new Error2Form();

    // トライキャッチで主キーの重複をキャッチ
    try {
      animeService.insertByAnime(insertForm.getTitle(), insertForm.getGenre(), insertForm.getEpisodes(),
          insertForm.getBroadcast(), insertForm.getSeason());
    } catch (DuplicateKeyException e) {

      model.addAttribute("error2Form", error2form);
      return "error2";
    }
    animeForm.setGenrelist(animeService.selectGenreAll());
    model.addAttribute("animeForm", animeForm);

    return "anime";

  }

  // 追加初期画面
  @PostMapping("/insert")
  public String insertExecute(@ModelAttribute InsertForm insertForm, Model model) {

  
    insertForm.setGenrelist(animeService.selectGenreInsert());

    model.addAttribute("insertForm", insertForm);

    return "insert";
  }

  // 更新初期画面（/updateは受ける側）
  @PostMapping("/update")
  public String updateExecute(@ModelAttribute UpdateForm updateForm, Model model) {

    List<AnimeEntity> list;

    list = animeService.selectAll();

    updateForm.setList(list);
    model.addAttribute("updateForm", updateForm);

    // HTMLのupdate.HTMLにとばす
    return "update";
  }

  // 更新処理画面（
  @RequestMapping(value = "/", params = "update", method = RequestMethod.POST)
  public String updateProces(@Validated UpdateForm updateForm, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      return "update";
    }

    List<AnimeEntity> list;
    AnimeForm animeForm = new AnimeForm();
    Error2Form error2Form = new Error2Form();

    try {
      animeService.updateByAnime(updateForm.getTitle(), updateForm.getGenre(), updateForm.getEpisodes(),
          updateForm.getBroadcast(), updateForm.getSeason());
    } catch (Exception e) {
      System.out.println(e);
      model.addAttribute("errorForm", error2Form);
      return "error2";
    }

    model.addAttribute("animeForm", animeForm);
    return "anime";

  }

  // 削除初期画面
  @PostMapping("/delete")
  public String deleteExcecute(@ModelAttribute DeleteForm deleteForm, Model model) {
    List<AnimeEntity> list;

    list = animeService.selectAll();

    deleteForm.setList(list);

    model.addAttribute("deleteForm", deleteForm);

    return "delete";
  }

  // 削除処理
  @RequestMapping(value = "/", params = "delete", method = RequestMethod.POST)
  public String deleteProsece(@Validated DeleteForm deleteForm, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {

      List<AnimeEntity> list;

      list = animeService.selectAll();

      deleteForm.setList(list);

      model.addAttribute("deleteForm", deleteForm);
      return "delete";
    }

    AnimeForm animeForm = new AnimeForm();

    animeService.deleteByAnime(deleteForm.getId());// , deleteForm.getSeason());

    model.addAttribute("animeForm", animeForm);
    return "anime";
  }
}
