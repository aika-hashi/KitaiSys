package jp.dev.kintaisys.domain.user.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.dev.kintaisys.domain.user.model.Kintai;
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
    public void signup(Kintai kintai) {
    	
            // 存在チェック?
    	
            boolean exists = repository.existsById(kintai.getId());
            if(exists) {
                throw new DataAccessException("ユーザーが既に存在"){};
            }
     
//            LocalDateTime date = new Date();   	
//        	kintai.setNowTime(date);
      	    
           
            // insert
            repository.save(kintai);
        }

    /** ユーザー取得(1件) */
    @Override
    public Kintai getKintaiOne(Integer id) {
        Optional<Kintai> option = repository.findById(id);
        Kintai kintai = option.orElse(null);
        return kintai;
    }

   
    

    /** 時刻更新(1件) */
    @Transactional
    @Override
    public void updateKintaiOne(Integer id, String loginId,LocalDateTime nowTime, LocalDateTime beginTime,LocalDateTime endTime,LocalDateTime outingName,LocalDateTime returnTime) {
     repository.updateKintai(id,loginId, nowTime,  beginTime, endTime, outingName,returnTime);
        
               
    	
    }
    
    /** ログインユーザー勤怠取得 */
    @Override
    public Kintai getKintai(Integer id) {
        return repository.findKintai(id);
    }
    
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
