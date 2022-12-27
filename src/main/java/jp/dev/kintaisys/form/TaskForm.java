package jp.dev.kintaisys.form;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class TaskForm {

private Integer id;	
private String loginId;	
private LocalDateTime nowTime;
private LocalDateTime beginTime;
private LocalDateTime endTime;
private LocalDateTime outingTime;
private LocalDateTime returnTime;
private Double breakTime;
private String comment;
private Double totalTime;
private List<String> calenderList;
private Integer year;
private Integer month;
private Integer date;
private String dayOfWeek;
private int monthCangeFlag;
//1の場合1、前月　2の場合、翌月




	
	
}
