package com.example.saibaikun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.saibaikun.entity.ActionRrkEntity;
import com.example.saibaikun.entity.LoginLogEntity;
import com.example.saibaikun.form.MainForm;
import com.example.saibaikun.form.SetupForm2;
import com.example.saibaikun.service.DateService;
import com.example.saibaikun.service.LoginService;
import com.example.saibaikun.service.SetupService;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    SetupService setupService;

    @Autowired
    DateService dateService;

    //開始ボタン押下（ログイン）（index2 → main）
    @RequestMapping(value = "/saibaikun", params = "login", method = RequestMethod.POST)
    public String start(@ModelAttribute SetupForm2 setupForm2, Model model) {

        //MainForm準備
        MainForm mainForm = new MainForm();

        //
        Integer saibaiDaichoId = setupForm2.getSaibaiDaichoId();
        Integer userId = setupForm2.getUserId();
        boolean result = true;
        System.out.println("★★："+saibaiDaichoId);

        //本日（yyyy/MM/dd HH:mm:ss.SSS）
        String datetime = dateService.getTimestamp();
        //本日（yyyy/MM/dd）
        String date = dateService.getDateYmd();
        //前日（yyyy/MM/dd）
        String yestdate = dateService.getDateYmdYest();
        //前々日（yyyy/MM/dd）
        String dbyestdate = dateService.getDateYmdDbyest();
        //前々々日（yyyy/MM/dd）
        String tdbyestdate = dateService.getDateYmdTDbyest();


        //T_LOGINへの登録準備
        //前回ログイン時の情報を取得する
        LoginLogEntity loginLog = loginService.loginLogCheck(userId);
        //取得した履歴番号に+1する
        Integer rrkNo = loginLog.getRrkNo()+1;
        //インスタンス生成
        LoginLogEntity loginLogEntity = new LoginLogEntity();
        loginLogEntity.setUserId(userId);
        loginLogEntity.setRrkNo(rrkNo);
        loginLogEntity.setLoginTm(datetime);
        loginLogEntity.setZenkaiLoginTm(loginLog.getZenkaiLoginTm());

        //T_ACTION_RRKへの登録準備
        //「今日」のアクション履歴レコードがあるか確認する。あれば何もしない、なければデータ追加
        Integer actionRrkCheck = loginService.actionRrkCheck(saibaiDaichoId,date);
        ActionRrkEntity actionRrkEntity = new ActionRrkEntity();

        //レベルアップ判定
        //アクション履歴があるかチェック
        boolean yestDateCheck    = loginService.yestDateCheck(saibaiDaichoId,yestdate);
        boolean dbyestDateCheck  = loginService.dbyestDateCheck(saibaiDaichoId,dbyestdate);
        boolean tdbyestDateCheck = loginService.tdbyestDateCheck(saibaiDaichoId,tdbyestdate);

        //現在のレベル確認
        Integer level = loginService.levelCheck(saibaiDaichoId);
        System.out.println("★レベル："+level);

        //「前日」のアクション履歴があればチェック実施
        if(yestDateCheck) {
            //前日のデータ取得
            ActionRrkEntity yestActionRrkEntity = loginService.getYestDate(saibaiDaichoId,yestdate);
            Integer actionCtount2 = yestActionRrkEntity.getActionCount2();
            String yestCheckedFlg = yestActionRrkEntity.getCheckFlg();

            //フラグ未チェック
            if(!yestCheckedFlg.equals("1")){
                //さいばいくんのレベルが0
                if(level == 0) {
                    // 前日のそうじカウントが3
                    if(actionCtount2 == 3){
                        //前日のレコードのチェック済みフラグをONする
                        loginService.updYestDate(saibaiDaichoId,yestdate);
                    }

                    //前々日、前々々日のレコードがあれば、レベルアップチェック
                    if(dbyestDateCheck && tdbyestDateCheck){
                        ActionRrkEntity dbyestActionRrkEntity  = loginService.getDbYestDate(saibaiDaichoId,dbyestdate);
                        ActionRrkEntity tdbyestActionRrkEntity = loginService.getTDbYestDate(saibaiDaichoId,tdbyestdate);
                        String dbyestCheckedFlg  = dbyestActionRrkEntity.getCheckFlg();
                        String tdbyestCheckedFlg = tdbyestActionRrkEntity.getCheckFlg();
                                
                        // チェック済みフラグONが3日続いたらレベルアップする
                        if( yestCheckedFlg.equals("1") && dbyestCheckedFlg.equals("1") && tdbyestCheckedFlg.equals("1") ){
                            level = level+1;
                            loginService.updLevelUp(saibaiDaichoId,level);
                            
                            // 成長時にアニメーションつけたい
                        }

                    }

                // レベル1以上の場合
                }else{
                        // if 前日のごはんカウントが3、そうじカウントが3、あそびカウントが1 だったら、
                        //    前日のレコードのチェック済みフラグをON（1）にする
                }
            }

        }else{

        }


        if(actionRrkCheck == 0) {
            actionRrkEntity.setSaibaiDaichoId(saibaiDaichoId);
            actionRrkEntity.setActionYmd(date);
            //登録
            result = loginService.loginExecute(loginLogEntity,actionRrkEntity);
        }else{
            //登録
            result = loginService.loginExecute2(loginLogEntity);
        }

        if (!result ) {
            return "error";
        }

        //さいばい台帳からステータス情報を取得する
        //取得したステータス情報をMainFormに設定する
        mainForm.setStatus(loginService.getSaibaiStatus(saibaiDaichoId,date));
        mainForm.setSaibaiDaichoId(saibaiDaichoId);

        // // viewにformをセット
        model.addAttribute("mainForm", mainForm);

        // /saibaikun/index.htmlを表示する
        return "/saibaikun/index";
    }

}