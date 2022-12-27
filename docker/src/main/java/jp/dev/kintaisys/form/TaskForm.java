package jp.dev.kintaisys.form;

import java.sql.Timestamp;
import java.util.List;

import jp.dev.kintaisys.domain.user.model.Kintai;
import lombok.Data;

@Data
public class TaskForm {

	private long id;
	private String loginId;
	private Timestamp nowTime;
	private Timestamp beginTime;
	private Timestamp endTime;
	private Timestamp outingTime;
	private Timestamp returnTime;
	private Double breakTime;
	private String comment;
	private Double totalTime;
	private String[] calenderList;
	private String day;
	private int year;
	private int month;
	private int date;
	private String dayOfWeek;
	private int monthCangeFlag;
	private List<Timestamp> startTimeList;
	private List<Timestamp> beginTimeList;
	private List<Timestamp> endTimeList;
	private List<Timestamp> outingTimeList;
	private List<Timestamp> returnTimeList;
	private List<Double> TotalTimeList;
	private List<Kintai> KintaiList;

//1の場合1、前月　2の場合、翌月

}
