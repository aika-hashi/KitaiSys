package jp.dev.kintaisys.domain.user.service;

import java.sql.Timestamp;
import java.util.List;

import jp.dev.kintaisys.domain.user.model.Kintai;
import jp.dev.kintaisys.domain.user.model.MUser;

public interface TaskService {
	
	 /** ログインユーザの勤怠情報取得 */
    public Kintai getKintaiOne(Long id);
    
    /**追加 ログインユーザ勤怠情報取得 */
	public List<Kintai> getKintaiList(Long id, int year,int month);

	 /** 時刻登録 */
    public void signup(MUser user,Kintai kintai);

	
	/** 時刻更新 */
    public void updateKintaiOne(long id,String loginId,Timestamp nowTime, Timestamp beginTime,Timestamp endTime,Timestamp outingName,Timestamp returnTime);
    
    /** ログインユーザー勤怠情報取得 */
    public Kintai getKintai(long id);
    
    
    /** 日時一覧画面(list) */
    //一覧を前月ないし翌月に更新
//    public void updateTaskListOne(Integer month);
    /** 月取得 */
//    public String getMonth(Integer id);
    
    
   
    
    
   
}
