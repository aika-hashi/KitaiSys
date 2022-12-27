package jp.dev.kintaisys.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.dev.kintaisys.domain.user.model.Kintai;

public interface TaskRepository extends JpaRepository<Kintai, Integer> {
	
	
	 /** ログインユーザ勤務時間情報の取得  一応*/
    @Query("select kintai"
            + " from Kintai kintai"
            + " where id = :id")
    public Kintai findKintai(@Param("id") Integer id);
	
    /** 時刻更新 */
    @Modifying
    @Query("update Kintai"
            + " set"
            + "     id = :id"
            + "   , loginId = :loginId"
            + "   , nowTime = :nowTime"
            + "   , beginTime = :beginTime"
            + "   , endTime = :endTime"
            + "   , outingTime = :outingTime"
            + "   , returnTime = :returnTime"
            + " where"
            + "   id = :id")
    public Integer updateKintai(
    		@Param("id") Integer id,
    		@Param("loginId") String loginId,
    		@Param("nowTime") LocalDateTime nowTime,
    		@Param("beginTime") LocalDateTime beginTime,
            @Param("endTime") LocalDateTime endTime,
            @Param("outingTime") LocalDateTime outingTime,
            @Param("returnTime") LocalDateTime returnTime);

}

