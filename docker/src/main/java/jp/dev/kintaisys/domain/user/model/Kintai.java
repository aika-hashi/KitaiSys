package jp.dev.kintaisys.domain.user.model;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tr_kintai")
public class Kintai {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "login_id")
	private String loginId;
	@Column(name = "now_time")
	private Timestamp nowTime;
	@Column(name = "begin_time")
	private Timestamp beginTime;
	@Column(name = "end_time")
	private Timestamp endTime;
	@Column(name = "outing_time")
	private Timestamp outingTime;
	@Column(name = "return_time")
	private Timestamp returnTime;
	@Column(name = "break_time")
	private Double breakTime;
	@Column(name = "total_time")
	private Time totalTime;
	@Column(name = "comment")
	private String comment;
	@Column(name = "year")
	private int year;
	@Column(name = "month")
	private int month;
	@Column(name = "date")
	private int date;
//	@Column(name = "calender_list")
//	private String[] calenderList;
	 

//	@OneToMany
//    @JoinColumn(insertable=false, updatable=false, name = "projectId")
//    private List<Kintai> kintaiList ;

}
