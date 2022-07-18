// package com.example.saibaikun.controller;

// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;

// import com.example.saibaikun.form.MainForm;
// import com.example.saibaikun.form.SetupForm;

// @Controller
// public class ActionController {

//     // メイン画面初期表示
//     @RequestMapping(value = "/saibaikun/", params = "start", method = RequestMethod.POST)
//     public String postExecute(@ModelAttribute SetupForm setupForm, Model model) {

//         MainForm mainForm = new MainForm();

//         // // viewにformをセット
//         model.addAttribute("mainForm", mainForm);

//         // /saibaikun/index.htmlを表示する
//         return "/saibaikun/index";
//     }

// }