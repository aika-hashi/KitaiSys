package jp.dev.kintaisys.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.dev.kintaisys.domain.user.model.Task;

@Mapper
public interface TaskMapper {
	
	 /** 勤怠情報登録 */
    public int insertOne(Task task);

    /** 勤怠情報取得 */
    public List<Task> findMany(Task task);

	
	 /** ログインユーザの勤怠情報取得 */
    public Task findOne(String loginId);
    
    
    


}
