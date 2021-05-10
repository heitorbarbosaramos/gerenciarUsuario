package com.heitor.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class CentralView {
	
	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		return "central";
	}
	
	@GetMapping(value = "login")
	public String login() {
		return "login";
	}

}
