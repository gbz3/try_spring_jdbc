package com.github.gbz3.try_spring_webmvc.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

	@RequestMapping("/")
	public String home() {
		return "index";
	}

}
