package jp.dev.kintaisys.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.dev.kintaisys.domain.user.model.Kintai;

public interface TaskRepository extends JpaRepository<Kintai, Long> {
	
	
	 /** ログインユーザ勤務時間情報の取得  一応*/
    @Query("select kintai"
            + " from Kintai kintai"
            + " where id = :id")
    public Kintai findKintai(@Param("id") long id);
    
    /** ログインユーザ勤務時間情報の取得  List ver*/
    @Query("select kintai"
            + " from Kintai kintai"
            + " where id = :id"
            + " and year = :year"
            + " and month = :month")
    public List<Kintai> findKintaiList(
    		@Param("id") long id,
    		@Param("year") int year,
    		@Param("month") int month
    		);
	
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
    		@Param("id") long id,
    		@Param("loginId") String loginId,
    		@Param("nowTime") Timestamp nowTime,
    		@Param("beginTime") Timestamp beginTime,
            @Param("endTime") Timestamp endTime,
            @Param("outingTime") Timestamp outingTime,
            @Param("returnTime") Timestamp returnTime);

}

