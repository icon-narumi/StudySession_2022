package com.example.saibaikun.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.saibaikun.bean.GetLoginInfoBean;
import com.example.saibaikun.bean.SaibaikunStatusBean;
import com.example.saibaikun.entity.ActionRrkEntity;
import com.example.saibaikun.entity.LoginLogEntity;
import com.example.saibaikun.mapper.ActionRrkMapper;
import com.example.saibaikun.mapper.LoginLogMapper;
import com.example.saibaikun.mapper.SaibaikunMapper;
import com.example.saibaikun.mapper.StatusMapper;


@Service
public class LoginService {

    @Autowired
    private SaibaikunMapper saibaikunMapper;
    
    @Autowired
    private StatusMapper statusMapper;

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Autowired
    private ActionRrkMapper actionRrkMapper;


    //キャラクターリスト取得（ログイン）
    public List<GetLoginInfoBean> getUserCharacterList(Integer userId) {
        return saibaikunMapper.selectSaibaikun(userId);
    }

    //さいばいくんステータス表示
    public SaibaikunStatusBean getSaibaiStatus(Integer saibaiDaichoId,String date) {
        return statusMapper.getStatus(saibaiDaichoId,date);
    }

    //ログインログの確認（履歴番号、前回ログイン日時）
    public LoginLogEntity loginLogCheck(Integer userId) {
        return loginLogMapper.loginLogCheck(userId);
    }

    //アクション履歴の確認
    public Integer actionRrkCheck(Integer saibaiDaichoId,String date) {
        return actionRrkMapper.actionRrkCheck(saibaiDaichoId,date);
    }

    //さいばいくんの状態確認
    public Integer levelCheck(Integer saibaiDaichoId) {
        return saibaikunMapper.levelCheck(saibaiDaichoId);
    }

    //前日チェック
    //アクション履歴有無
    public Integer yestDateCheck(Integer saibaiDaichoId,String yestdate) {
        return actionRrkMapper.yestDateCheck(saibaiDaichoId,yestdate);
    }
    //データ取得
    public ActionRrkEntity getYestDate(Integer saibaiDaichoId,String yestdate) {
        return actionRrkMapper.getYestDate(saibaiDaichoId,yestdate);
    }
    //チェック済みフラグをON
    public void updYestDate(Integer saibaiDaichoId,String yestdate) {
        actionRrkMapper.updYestDate(saibaiDaichoId,yestdate);
    }


    //前々日チェック
    //アクション履歴有無
    public Integer dbyestDateCheck(Integer saibaiDaichoId,String dbyestdate) {
        return actionRrkMapper.dbyestDateCheck(saibaiDaichoId,dbyestdate);
    }
    //データ取得
    public ActionRrkEntity getDbYestDate(Integer saibaiDaichoId,String dbyestdate) {
        return actionRrkMapper.getDbYestDate(saibaiDaichoId,dbyestdate);
    }

    //前々々日チェック
    //アクション履歴有無
    public Integer tdbyestDateCheck(Integer saibaiDaichoId,String tdbyestdate) {
        return actionRrkMapper.tdbyestDateCheck(saibaiDaichoId,tdbyestdate);
    }
    //データ取得
    public ActionRrkEntity getTDbYestDate(Integer saibaiDaichoId,String tdbyestdate) {
        return actionRrkMapper.getTDbYestDate(saibaiDaichoId,tdbyestdate);
    }

    //レベル操作
    public void updLevel(Integer saibaiDaichoId,Integer level) {
        saibaikunMapper.updLevel(saibaiDaichoId,level);
    }

	public boolean loginExecute(LoginLogEntity loginLogEntity,ActionRrkEntity actionRrkEntity) 
    {
        try {
            loginLogMapper.loginLogAddEntity(loginLogEntity);
            System.out.println("ログインログ登録成功だよ");

        } catch (Exception e) {
            System.out.println("ログインログ台帳登録失敗-------------------str");
            System.out.println(e);
            System.out.println("ログインログ台帳登録失敗-------------------end");

            return false;
        }

        try {
            actionRrkMapper.actionRrkAddEntity(actionRrkEntity);
            System.out.println("アクション履歴登録成功だよ");

        } catch (Exception e) {
            System.out.println("アクション履歴登録失敗-------------------str");
            System.out.println(e);
            System.out.println("アクション履歴登録失敗-------------------end");

            return false;
        }

        return true;
    }

    //同日にログイン済みの場合
	public boolean loginExecute2(LoginLogEntity loginLogEntity) 
    {
        try {
            loginLogMapper.loginLogAddEntity(loginLogEntity);
            System.out.println("ログインログ登録成功だよ");

        } catch (Exception e) {
            System.out.println("ログインログ台帳登録失敗-------------------str");
            System.out.println(e);
            System.out.println("ログインログ台帳登録失敗-------------------end");

            return false;
        }
        return true;

    }

}
