package jp.dev.kintaisys.domain.user.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.dev.kintaisys.domain.user.model.Kintai;
import jp.dev.kintaisys.domain.user.model.MUser;
import jp.dev.kintaisys.domain.user.service.TaskService;
import jp.dev.kintaisys.repository.TaskMapper;
import jp.dev.kintaisys.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository repository;
	
	 @Autowired
	 private TaskMapper mapper;
	 
	
	 

	
	 /** ユーザー登録 */
    @Transactional
    @Override
    public void signup(MUser user, Kintai kintai) {
    	
            // 存在チェック?
    	
            boolean exists = repository.existsById(user.getId());
            if(exists) {
                throw new DataAccessException("ユーザーが既に存在"){};
            }
     
//            LocalDateTime date = new Date();   	
//        	kintai.setNowTime(date);user.getId()
      	    
          
            // insert
            repository.save(kintai);
            kintai.setId(user.getId());
            kintai.setLoginId(user.getLoginId());
        }

    /** ユーザー取得(1件) */
    @Override
    public Kintai getKintaiOne(Long id) {
        Optional<Kintai> option = repository.findById(id);
        Kintai kintai = option.orElse(null);
        return kintai;
    }

   
    

    /** 時刻更新(1件) */
    @Transactional
    @Override
    public void updateKintaiOne(long id, String loginId,Timestamp nowTime,Timestamp beginTime,Timestamp endTime,Timestamp outingName,Timestamp returnTime) {
     repository.updateKintai(id,loginId, nowTime,  beginTime, endTime, outingName,returnTime);
        
               
    	
    }
    
    /** ログインユーザー勤怠取得 */
    @Override
    public Kintai getKintai(long id) {
        return repository.findKintai(id);
    }
    
    /**追加 ログインユーザ勤怠情報取得 */
	public List<Kintai> getKintaiList(Long id, int year,int month){
		return repository.findKintaiList(id,year,month);
	};

    
    /** 日時一覧画面(list) */
    //一覧を前月ないし翌月に更新
//    @Transactional
//    @Override
//    public void updateTaskListOne(Integer month) {
//    	
//    	
//    };
    
//    /** 月取得 */
//    public String getMonth(Integer month) {
//    	
//    	return month;
//    }
    
}
