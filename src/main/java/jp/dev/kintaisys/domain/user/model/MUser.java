package jp.dev.kintaisys.domain.user.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "m_user")
public class MUser   {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String loginId;
	private String roleId;
	private String password;
	private String userName;
	private Date birthday;
	private Integer age;
	private Integer gender;
	private Integer departmentId;
	

	

	
	

}
