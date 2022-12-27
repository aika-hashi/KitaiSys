package jp.dev.kintaisys.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.dev.kintaisys.form.TaskForm;

@Controller
@RequestMapping("/task")
public class TaskListController {
	
	/** トップニ時作業入力画面を表示 */
	@GetMapping("/list")
	//@GetMapping("/top/{loginId:.+}")
	public String getTopHtml(TaskForm form,Model model) {
		
		//カレンダー
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		//月設定 フラグいらないかも
		    //今月
		int month = (cal.get(Calendar.MONTH))+1;
		if(form.getMonthCangeFlag()==1) {
			//前月
	     month = (cal.get(Calendar.MONTH));
		}else if(form.getMonthCangeFlag()==2) {
			//翌月
		 month = (cal.get(Calendar.MONTH))+2;
		}
		
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
		
		model.addAttribute("taskForm", form);
		return "task/list";
	

}
	
	/** トップ画面に時刻データを渡す処理 */
	 @PostMapping("/list")
	    public String postTopHtml(@ModelAttribute TaskForm form, Model model) {
	
		 
		 return "redirect:/task/list";
		 
	 }

}
