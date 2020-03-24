package com.workreport.sample.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

	@GetMapping("/menu")
		public String menuForm(Principal principal, Model model) {
	        Authentication authentication = (Authentication)principal;
	        String username = authentication.getName();
	        model.addAttribute("username", username);
	        return "menu";
	    }
}
