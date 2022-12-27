package jp.dev.kintaisys.controller;

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
import jp.dev.kintaisys.form.TaskForm;

@Controller
@RequestMapping("/task")
public class TaskTopController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;

	/** トップ画面を表示 */
	@GetMapping("/top")
	// @GetMapping("/top/{loginId:.+}")
	public String getTopHtml(Model model,TaskForm form) {

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
if (muserR == null) {
			// 例外処理
			// TODO
		}

		// ここムズイ
//		Integer id = (userService.getUserOne(loginId)).getMuserKey().getId();

		// formをTaskクラスへ変換
//        Task task= modelMapper.map(form, Task.class);

//        
//        task.setNowTime(null);       
//        task.setBeginTime(null);
//        task.setEndTime(null);
//        task.setOutingTime(null); 
//        task.setReturnTime(null);       
//        // Taskrをformに変換
//        form = modelMapper.map(task, TaskForm.class);
//      //現在時刻をformにセット
//        Date date = new Date();
		//TaskForm form = new TaskForm();

		
//        form.setBeginTime(form.getBeginTime());
//       form.setEndTime(form.getEndTime());
//        form.setOutingTime(form.getOutingTime());
//      form.setReturnTime(form.getReturnTime());

//        userService.getUsers(user);
		// MUser user= userService.getLoginUser(form.getLoginId());

       

		
//        /** loginIdの受け渡しについて */★
		Kintai kintai = taskService.getKintaiOne(muserR.getId());
		
		
//       if(kintai != null) {
//         //Kintaiをformに変換

		if(kintai != null) {
		kintai.setEndTime(kintai.getEndTime());
		form = modelMapper.map(kintai, TaskForm.class);
		}
//        // formをTaskクラスに変換
////            task1 = modelMapper.map(form, Task.class);
//       }
//      
		// Modelに登録
		//Model model = new ExtendedModelMap();
		model.addAttribute("taskForm", form);

		// ログインユーザのトップ画面を表示

		return "task/top";
	}

	/** トップ画面に時刻データを渡す処理 */
	@PostMapping("/top")
	public String postTopHtml(@ModelAttribute TaskForm form, Model model) {
//
//    	 
//        if(!(form.getNowTime().equals(form.gedtBeginTime()))) {
//            form.setBeginTime(form.getNowTime());		
//        	}else if(!(form.getNowTime().equals(form.getEndTime()))){
//            form.setEndTime(form.getNowTime());
//        	}else if(!(form.getNowTime().equals(form.getOutingTime()))){
//        		form.setOutingTime(form.getNowTime());
//        	}else if(!(form.getNowTime().equals(form.getReturnTime()))){	
//    		form.setReturnTime(form.getNowTime());   	
//        	}
//    	
   	//form.setKintaiList(form);
		//form.setBeginTimeList(form.getBeginTime());
//        	
		model.addAttribute("taskForm", form);
//    	
		return "redirect:/task/top";

	}

}