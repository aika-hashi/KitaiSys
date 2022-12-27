package jp.dev.kintaisys.domain.user.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="tr_task")
public class Task {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer MProjectId;
	private Integer projectId;
	private Double totalTime;
	private String comment;
	
	

}
