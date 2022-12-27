package jp.dev.kintaisys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jp.dev.kintaisys.domain.user.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
    private UserService service;

	/** ログイン画面を表示 */
	@GetMapping("/login")
	public String getLogin() {
		return "login/login";
	}

	/** トップ画面にリダイレクト */
	@PostMapping("/login")
	public String postLogin() {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	      String name = auth.getName();//get logged in username
//	      System.out.println("name="+name);		
		
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	String loginId = ((UserDetails) principal).getUsername();
//		// ユーザー情報取得
//	    MUser loginUser = service.getLoginUser(loginId);
//
//	    // ユーザーが存在しない場合
//	    if(loginUser == null) {
//	        throw new UsernameNotFoundException("user not found");
//	    }
		return "redirect:/task/top";

	}
}
