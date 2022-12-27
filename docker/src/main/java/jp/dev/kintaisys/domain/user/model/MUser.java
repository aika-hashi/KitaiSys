package jp.dev.kintaisys.domain.user.model;

import java.util.Date;

import javax.persistence.Column;
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
	 @Column(name="id")
	private long id;	
	 @Column(name="login_id")
	private String loginId;
	 @Column(name="role_id")
	private String roleId;
	 @Column(name="password")
	private String password;
	 @Column(name="userName")
	private String userName;
	 @Column(name="birthday")
	private Date birthday;
	 @Column(name="age")
	private Integer age;
	 @Column(name="gender")
	private Integer gender;
	//private Integer departmentId;
	

	

	
	

}
