package com.workreport.sample.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String rootForm(Model model) {
        return "login";
    }

	@RequestMapping("/login")
    public String showLoginForm(
    		@RequestParam(name="error", defaultValue="") String error,
    		Model model) {

		if(!error.equals("")) {
			// パラメータ"error"に値の指定があれば、認証エラーとみなす
			model.addAttribute("errorMessage", "認証に失敗しました");
		}

        //ログイン画面へ遷移
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccessForm(Principal principal, Model model) {
        Authentication authentication = (Authentication)principal;
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "loginSuccess";
    }

    @GetMapping("/logoutSuccess")
    public String logoutSuccessForm( Model model) {
//        return "logoutSuccess";
    	return "redirect:rootForm";
    }

    @GetMapping("/invalidSession")
    public String invalidSessionForm(Model model) {
        return "login";
    }

}
