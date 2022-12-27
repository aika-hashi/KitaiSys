package jp.dev.kintaisys.form;

import java.util.Date;

import jp.dev.kintaisys.domain.user.model.Department;
import lombok.Data;

@Data
public class UserDetailForm {
    private String loginId;
    private String password;
    private String userName;
    private Date birthday;
    private Integer age;
    private Integer gender;
    private Department department;
   
}