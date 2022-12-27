package jp.dev.kintaisys.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.dev.kintaisys.domain.user.model.Kintai;
import jp.dev.kintaisys.domain.user.model.MUser;
import jp.dev.kintaisys.domain.user.service.TaskService;
import jp.dev.kintaisys.domain.user.service.UserService;
import jp.dev.kintaisys.form.KintaiData;
import jp.dev.kintaisys.form.TaskForm;

@Controller
@RequestMapping("/task")
public class TaskListController {

	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private ModelMapper modelMapper;

	/** トップニ時作業入力画面を表示 */
	@GetMapping("/list")
	// @GetMapping("/top/{loginId:.+}")
	public String getTopHtml(TaskForm form, Model model,KintaiData data) {

		// SecurityConfigからの認証情報の取得 いる？
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String loginId;
		if (principal instanceof UserDetails) {
			loginId = ((UserDetails) principal).getUsername();
		} else {
			loginId = principal.toString();
		}

		List<MUser> userList = userService.findAll();
		MUser muserR = null;
		for (MUser muser : userList) {

			String loginIdR = muser.getLoginId();
			if (loginId.equals(loginIdR)) {
				muserR = muser;
				break;

			}

		}
		System.out.println(muserR);
		// form = modelMapper.map(muserR, TaskForm.class);

		form.setId(muserR.getId());
		form.setLoginId(muserR.getLoginId());

		if (muserR == null) {
			// 例外処理
			// TODO
		}

		// カレンダー
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		// 月設定 フラグいらないかも
		// 今月
		int month = (cal.get(Calendar.MONTH)) + 1;
		if (form.getMonthCangeFlag() == 1) {
			// 前月
			month = (cal.get(Calendar.MONTH));
		} else if (form.getMonthCangeFlag() == 2) {
			// 翌月
			month = (cal.get(Calendar.MONTH)) + 2;
		}

		int date = cal.get(Calendar.DATE);
		// 曜日
		String dayOfWeek = null;
		int calDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

		if (calDayOfWeek == Calendar.SUNDAY) {
			dayOfWeek = "日"; // Calendar.SUNDAY:1 （値。意味はない）
		} else if (calDayOfWeek == Calendar.MONDAY) {
			dayOfWeek = "月"; // Calendar.MONDAY:2
		} else if (calDayOfWeek == Calendar.TUESDAY) {
			dayOfWeek = "火"; // Calendar.TUESDAY:3
		} else if (calDayOfWeek == Calendar.WEDNESDAY) {
			dayOfWeek = "水"; // Calendar.WEDNESDAY:4
		} else if (calDayOfWeek == Calendar.THURSDAY) {
			dayOfWeek = "木"; // Calendar.THURSDAY:5
		} else if (calDayOfWeek == Calendar.FRIDAY) {
			dayOfWeek = "金"; // Calendar.FRIDAY:6
		} else if (calDayOfWeek == Calendar.SATURDAY) {
			dayOfWeek = "土"; // Calendar.SATURDAY:7
		}

		// formにセット
		form.setYear(year);
		form.setMonth(month);
		form.setDate(date);
		form.setDayOfWeek(dayOfWeek);

		// ひと月のに数取得
		String[] calenderList = new String[32];
		Calendar c = new GregorianCalendar(year, month, 1);
		int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);

		String[] week_name = { "日", "月", "火", "水", "木", "金", "土" };
		date = 1;
		cal.set(Calendar.DAY_OF_MONTH, 1);
		// String day1 = month + "/" + date + "(" +
		// week_name[cal.get(Calendar.DAY_OF_WEEK) - 1] + ")";
		for (int i = 1; i < days; i++) {

			// form.setDay(day1);
			// 曜日が
			calenderList[i]= month + "/" + date + "(" + week_name[cal.get(Calendar.DAY_OF_WEEK) - 1] + ")";

			date++;

			form.setCalenderList(calenderList);
		}

		Kintai kintai = taskService.getKintaiOne(form.getId());

		/** loginIdの受け渡しについて */
		List<Kintai> kintaiList = taskService.getKintaiList(kintai.getId(), kintai.getYear(), kintai.getMonth());

		List<KintaiData> kintaiDataList = new ArrayList<KintaiData>();		
//		if(kintaiList != null) {
//			//form = modelMapper.map(kintaiList, TaskForm.class);
//			form.setKintaiList(kintaiList);			
//			}

		// 出勤時間
		List<Timestamp> beginTimeList = new ArrayList<Timestamp>();
		for (int i = 1; i < days; i++) {
			beginTimeList.add(kintai.getBeginTime());		
			form.setBeginTimeList(beginTimeList);

		}

		// 退社時間
		List<Timestamp> endTimeList = new ArrayList<Timestamp>();
		for (int i = 1; i < days; i++) {
			endTimeList.add(kintai.getEndTime());
			form.setEndTimeList(endTimeList);
		}

		// 外出時間
		List<Timestamp> outingTimeList = new ArrayList<Timestamp>();
		for (int i = 1; i < days; i++) {
			outingTimeList.add(kintai.getOutingTime());
			form.setOutingTimeList(outingTimeList);

		}

		// 帰社時間
		List<Timestamp> returnTimeList = new ArrayList<Timestamp>();
		for (int i = 1; i < days; i++) {
			returnTimeList.add(kintai.getReturnTime());
			form.setReturnTimeList(returnTimeList);
		}

		// 作業時間
		List<Double> totalTimeList = new ArrayList<Double>();
		for (int i = 1; i < days; i++) {
			totalTimeList.add(form.getTotalTime());
			form.setTotalTimeList(totalTimeList);
		}


		
		form.setKintaiList(kintaiList);
		model.addAttribute("taskForm", form);
		model.addAttribute("kintaiList", kintaiList);
		model.addAttribute("kintaiDataList",kintaiDataList);
		return "task/list";
	}

	/** トップ画面に時刻データを渡す処理 */

	@PostMapping("/list")
	public String postTopHtml(@ModelAttribute TaskForm form, Model model) {

		return "redirect:/task/list";

	}

}
