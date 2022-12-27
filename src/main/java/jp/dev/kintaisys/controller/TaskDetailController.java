package jp.dev.kintaisys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.dev.kintaisys.form.TaskForm;

@Controller
@RequestMapping("/task")
public class TaskDetailController {
	
	/** トップニ時作業入力画面を表示 */
	@GetMapping("/detail")
	//@GetMapping("/top/{loginId:.+}")
	public String getTopHtml(TaskForm form,Model model) {
		
		return "task/detail";
	

}
	
	/** トップ画面に時刻データを渡す処理 */
	 @PostMapping("/detail")
	    public String postTopHtml(@ModelAttribute TaskForm form, Model model) {
	
		 
		 return "redirect:/task/detail";
		 
	 }
}
