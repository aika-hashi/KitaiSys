package jp.dev.kintaisys.domain.user.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="tr_kintai")
public class Kintai {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String loginId;	
	private LocalDateTime nowTime;
	private LocalDateTime beginTime;
	private LocalDateTime endTime;
	private LocalDateTime outingTime;
	private LocalDateTime returnTime;
	private Double breakTime;
	private String comment;

	    

//	@OneToMany
//    @JoinColumn(insertable=false, updatable=false, name = "projectId")
//    private List<Kintai> kintaiList ;
    
	
	    
	    
	}



