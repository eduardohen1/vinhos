package com.ehSoluces.vinhos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;


@Controller
public class SegurancaController {
	
	@RequestMapping("/login")
	public String login(@AuthenticationPrincipal User user){
		if(user != null){
			return "redirect:/vinhos";
		}
		return "login";
	}
	
}
