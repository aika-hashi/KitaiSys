package jp.dev.kintaisys.form;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class KintaiData {
	private long id;

	private String loginId;

	private Timestamp nowTime;

	private Timestamp beginTime;

	private Timestamp endTime;

	private Timestamp outingTime;

	private Timestamp returnTime;

	private Double breakTime;

	private Double totalTime;

	private String comment;

	//日付
	private String date;

}
