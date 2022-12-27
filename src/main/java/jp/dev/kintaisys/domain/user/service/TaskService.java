package jp.dev.kintaisys.domain.user.service;

import java.time.LocalDateTime;

import jp.dev.kintaisys.domain.user.model.Kintai;

public interface TaskService {
	
	 /** ログインユーザの勤怠情報取得 */
    public Kintai getKintaiOne(Integer id);

	 /** 時刻登録 */
    public void signup(Kintai kintai);

	
	/** 時刻更新 */
    public void updateKintaiOne(Integer id,String loginId,LocalDateTime nowTime, LocalDateTime beginTime,LocalDateTime endTime,LocalDateTime outingName,LocalDateTime returnTime);
    
    /** ログインユーザー勤怠情報取得 */
    public Kintai getKintai(Integer id);
    
    
    /** 日時一覧画面(list) */
    //一覧を前月ないし翌月に更新
//    public void updateTaskListOne(Integer month);
    /** 月取得 */
//    public String getMonth(Integer id);
    
    
   
    
    
   
}
