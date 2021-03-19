package com.bingo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@ResponseBody
	@RequestMapping(value = "/test")
	public String doSomeThing() {

		return "admin success";
	}
}
