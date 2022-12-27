package jp.dev.kintaisys.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.dev.kintaisys.domain.user.model.MUser;
import jp.dev.kintaisys.domain.user.service.UserService;
import jp.dev.kintaisys.form.UserDetailForm;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserDetailController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /** ユーザー詳細画面を表示 */
    @GetMapping("/detail/{userKey:.+}")
    public String getUser(UserDetailForm form, Model model, @PathVariable("loginId") String loginId) {

        // ユーザーを1件取得
        MUser user = userService.getUserOne(loginId);
        user.setPassword(null);

        // MUserをformに変換
        form = modelMapper.map(user, UserDetailForm.class);


        // Modelに登録
        model.addAttribute("userDetailForm", form);

        // ユーザー詳細画面を表示
        return "user/detail";
    }

    /** ユーザー更新処理 */
    @PostMapping(value = "/detail", params = "update")
    public String updateUser(UserDetailForm form, Model model) {

        try {
            // ユーザーを更新
            userService.updateUserOne(form.getLoginId(),
                    form.getPassword(),
                    form.getUserName());
        } catch (Exception e) {
            log.error("ユーザー更新でエラー", e);
        }
        // ユーザー一覧画面にリダイレクト
        return "redirect:/user/list";
    }

    /** ユーザー削除処理 */
    @PostMapping(value = "/detail", params = "delete")
    public String deleteUser(UserDetailForm form, Model model) {
    

    	 MUser user = modelMapper.map(form, MUser.class);
        // ユーザーを削除
        userService.deleteUserOne(user.getLoginId());

        // ユーザー一覧画面にリダイレクト
        return "redirect:/user/list";
    }
}
