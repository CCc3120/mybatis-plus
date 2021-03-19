package com.bingo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	// @ResponseBody
	@RequestMapping(value = "/info")
	public String login() {
		// modelAndView.
		// 49d2d14d-8aa1-4229-8124-73c2a98ae67f
		String userDetails = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userDetails = ((UserDetails) principal).getUsername();
		} else {
			userDetails = principal.toString();
		}
		return userDetails;
	}

	@ResponseBody
	@RequestMapping(value = "/test")
	public String doSomeThing() {

		return "user success";
	}
}
