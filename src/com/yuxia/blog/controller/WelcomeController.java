package com.yuxia.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@RequestMapping("/")
	public String login(){
		return "redirect:user/login";
	}
	@RequestMapping("/*")
	public String error404(){
		return "page_404";
	}
}
