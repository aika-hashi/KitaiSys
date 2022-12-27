package jp.dev.kintaisys.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	/** ログイン画面を表示　*/
	@GetMapping("/login")
	public String getLogin() {
		return "login/login";
	}
	
	/** トップ画面にリダイレクト */
	@PostMapping("/login")
	public String postLogin() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();//get logged in username
	      System.out.println("name="+name);		
	      return "redirect:/task/top" ;

//		public String postLogin(SignupForm form) {
//			String loginId = form.getLoginId();
//			return "task/top/" + loginId;

}
}
