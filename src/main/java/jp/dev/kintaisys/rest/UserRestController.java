package jp.dev.kintaisys.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.dev.kintaisys.domain.user.model.MUser;
import jp.dev.kintaisys.domain.user.service.UserService;
import jp.dev.kintaisys.form.GroupOrder;
import jp.dev.kintaisys.form.SignupForm;
import jp.dev.kintaisys.form.UserDetailForm;
import jp.dev.kintaisys.form.UserListForm;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private  UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    /** ユーザーを検索 */
    @GetMapping("/get/list")
    public List<MUser> getUserList(UserListForm form) {

        // formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class);
        MUser userKey = modelMapper.map(form, MUser.class);

        // ユーザー一覧取得
        List<MUser> userList = userService.getUsers(user);
        return userList;
    }

    /** ユーザーを登録 */
    @PostMapping("/signup/rest")
    public RestResult postSignup(@Validated(GroupOrder.class) SignupForm form,
            BindingResult bindingResult, Locale locale) {

        // 入力チェック結果
        if (bindingResult.hasErrors()) {
            // チェック結果:NG
            Map<String, String> errors = new HashMap<>();

            // エラーメッセージ取得
            for (FieldError error : bindingResult.getFieldErrors()) {
                String message = messageSource.getMessage(error, locale);
                errors.put(error.getField(), message);
            }
            // エラー結果の返却
            return new RestResult(90, errors);
        }

        // formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class);
        MUser userKey = modelMapper.map(form, MUser.class);
        
        // ユーザー登録
        userService.signup(user);

        // 結果の返却
        return new RestResult(0, null);
    }

    /** ユーザーを更新 */
	@PutMapping("/update")
	public int updateUser(UserDetailForm form) {

//ユーザーを更新
		userService.updateUserOne(form.getLoginId(), form.getPassword(), form.getUserName());
		return 0;
	}

	/** ユーザーを削除 */
	@DeleteMapping("/delete")
	public int deleteUser(UserDetailForm form) {
		MUser user = modelMapper.map(form, MUser.class);
		//MUserKey userKey = modelMapper.map(form, MUserKey.class);
        
//ユーザーを削除
		userService.deleteUserOne(user.getLoginId());
		return 0;
	}
}
