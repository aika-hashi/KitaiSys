package jp.dev.kintaisys.rest;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.dev.kintaisys.domain.user.model.Kintai;
import jp.dev.kintaisys.domain.user.service.TaskService;
import jp.dev.kintaisys.form.GroupOrder;
import jp.dev.kintaisys.form.TaskForm;

@RestController
@RequestMapping("/task/top")
public class TaskTopRestController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MessageSource messageSource;

	/** 時刻登録 */
	@PostMapping("/signup/rest")
	public RestResult postSignup(@Validated(GroupOrder.class) TaskForm form, BindingResult bindingResult,
			Locale locale) {

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

		// formをKintaiクラスに変換
		Kintai kintai = modelMapper.map(form, Kintai.class);

		// ユーザー登録 一旦消す
//		taskService.signup(kintai);

		// 結果の返却
		return new RestResult(0, null);
	}

	/** 時刻更新 */
	@PutMapping("/update")
	public Kintai updateTask(Model model, Locale locale, TaskForm form) {
		// ユーザーを更新
		taskService.updateKintaiOne(form.getId(),form.getLoginId(),form.getNowTime(),form.getBeginTime(), form.getEndTime(), form.getOutingTime(), form.getReturnTime());

//		if (!(form.getNowTime().equals(form.getBeginTime()))) {
//			form.setBeginTime(form.getNowTime());
//		} else if (!(form.getNowTime().equals(form.getEndTime()))) {
//			form.setEndTime(form.getNowTime());
//		} else if (!(form.getNowTime().equals(form.getOutingTime()))) {
//			form.setOutingTime(form.getNowTime());
//		} else if (!(form.getNowTime().equals(form.getReturnTime()))) {
//			form.setReturnTime(form.getNowTime());
//		}
		// formをKintaiクラスに変換
				Kintai kintai = modelMapper.map(form, Kintai.class);

        //一応
		model.addAttribute("taskForm", form);

		return kintai;
	}

}
