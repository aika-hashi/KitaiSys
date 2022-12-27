package jp.dev.kintaisys.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.dev.kintaisys.domain.user.model.MUser;
import jp.dev.kintaisys.domain.user.service.TaskService;
import jp.dev.kintaisys.form.TaskForm;

@RestController
@RequestMapping("/task/list")
public class TaskListRestController {
	
	 @Autowired
	    private  TaskService taskService;

	    @Autowired
	    private ModelMapper modelMapper;

	    @Autowired
	    private MessageSource messageSource;

	    /** 見本　月検索 */
	    @GetMapping("/get/list")
	    public List<String> getUserList(TaskForm form) {

	        // formをMUserクラスに変換
	        MUser user = modelMapper.map(form, MUser.class);

//	        // ユーザー一覧取得
//	        List<MUser> userList = taskService.getMonth(user);
	       
	      //カレンダー
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			//月設定
			    //前月
			int month = (cal.get(Calendar.MONTH));
			int date = cal.get(Calendar.DATE);
			//曜日
			String dayOfWeek = null;
			int calDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			
			if (calDayOfWeek == Calendar.SUNDAY ) { 
				dayOfWeek = "日";   // Calendar.SUNDAY:1 （値。意味はない）
			}else if(calDayOfWeek == Calendar.MONDAY) {
				dayOfWeek = "月";   // Calendar.MONDAY:2
			}else if(calDayOfWeek == Calendar.TUESDAY) {
				dayOfWeek = "火";   // Calendar.TUESDAY:3
			}else if(calDayOfWeek == Calendar.WEDNESDAY) {
				dayOfWeek = "水";   // Calendar.WEDNESDAY:4
			}else if(calDayOfWeek == Calendar.THURSDAY) {
				dayOfWeek = "木";   // Calendar.THURSDAY:5
			}else if(calDayOfWeek == Calendar.FRIDAY) {
				dayOfWeek = "金";   // Calendar.FRIDAY:6
			}else if(calDayOfWeek == Calendar.SATURDAY) {
				dayOfWeek = "土";   // Calendar.SATURDAY:7
			}
			
			//ひと月のに数取得
			List<String> calenderList = new ArrayList<String>();
			Calendar c = new GregorianCalendar(year,month,1);
			int days=c.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			
			date = 1; 
			 for (int i = 1;i <= days;i++) {
			//曜日が
				calenderList.add(month+ "/" + date +"(" + dayOfWeek + ")");
				date++;
			
			}
			 return calenderList;
	    }

//	    /** 見本　ユーザーを登録 */
//	    @PostMapping("/signup/rest")
//	    public RestResult postSignup(@Validated(GroupOrder.class) SignupForm form,
//	            BindingResult bindingResult, Locale locale) {
//
//	        // 入力チェック結果
//	        if (bindingResult.hasErrors()) {
//	            // チェック結果:NG
//	            Map<String, String> errors = new HashMap<>();
//
//	            // エラーメッセージ取得
//	            for (FieldError error : bindingResult.getFieldErrors()) {
//	                String message = messageSource.getMessage(error, locale);
//	                errors.put(error.getField(), message);
//	            }
//	            // エラー結果の返却
//	            return new RestResult(90, errors);
//	        }
//
//	        // formをMUserクラスに変換
//	        MUser user = modelMapper.map(form, MUser.class);
//
//	        // ユーザー登録
//	        userService.signup(user);
//
//	        // 結果の返却
//	        return new RestResult(0, null);
//	    }

	    /** ユーザーを更新 */
		@PutMapping("/update")
		public int updateList(TaskForm form,Model model,HttpServletRequest request, HttpServletResponse response) {

	//ユーザーを更新
//	       taskService.updateTaskListOne(form.getMonth());
//			form.setMonth((form.getMonth())-1);	
//			model.addAttribute("taskForm", form);
			
			
			
			//カレンダー
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			//前月になるように
			int month = (cal.get(Calendar.MONTH));
			int date = cal.get(Calendar.DATE);
			//曜日
			String dayOfWeek = null;
			int calDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			
			if (calDayOfWeek == Calendar.SUNDAY ) { 
				dayOfWeek = "日";   // Calendar.SUNDAY:1 （値。意味はない）
			}else if(calDayOfWeek == Calendar.MONDAY) {
				dayOfWeek = "月";   // Calendar.MONDAY:2
			}else if(calDayOfWeek == Calendar.TUESDAY) {
				dayOfWeek = "火";   // Calendar.TUESDAY:3
			}else if(calDayOfWeek == Calendar.WEDNESDAY) {
				dayOfWeek = "水";   // Calendar.WEDNESDAY:4
			}else if(calDayOfWeek == Calendar.THURSDAY) {
				dayOfWeek = "木";   // Calendar.THURSDAY:5
			}else if(calDayOfWeek == Calendar.FRIDAY) {
				dayOfWeek = "金";   // Calendar.FRIDAY:6
			}else if(calDayOfWeek == Calendar.SATURDAY) {
				dayOfWeek = "土";   // Calendar.SATURDAY:7
			}
		       
		  
			
			//formにセット
			form.setYear(year);
			form.setMonth(month);
			form.setDate(date);
			form.setDayOfWeek(dayOfWeek);
			
			
			
			//ひと月のに数取得
			List<String> calenderList = new ArrayList<String>();
			Calendar c = new GregorianCalendar(year,month,1);
			int days=c.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			
			date = 1; 
			 for (int i = 1;i <= days;i++) {
			//曜日が
				calenderList.add(month+ "/" + date +"(" + dayOfWeek + ")");
				date++;
			
			}
			form.setCalenderList(calenderList);	
			
			//前月monthCangeFlagに1を代入
			form.setMonthCangeFlag(1);
			model.addAttribute("taskForm", form);
			return 0;
		}

		/** 見本　ユーザーを削除 */
//		@DeleteMapping("/delete")
//		public int deleteUser(UserDetailForm form) {
//	//ユーザーを削除
//			userService.deleteUserOne(form.getLoginId());
//			return 0;
//		}
//	}
//
//	

}
