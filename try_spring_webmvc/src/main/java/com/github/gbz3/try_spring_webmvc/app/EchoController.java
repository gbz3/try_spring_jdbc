package com.github.gbz3.try_spring_webmvc.app;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("echo")
public class EchoController {

	@RequestMapping(method = RequestMethod.GET)
	public String viewInput(Model model) {
		EchoForm form = new EchoForm();
		model.addAttribute(form);
		return "echo/input";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String echo(@Validated EchoForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			//return "echo/input";
			throw new IllegalStateException("dummy.");
		}
		return "echo/output";
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException(Exception e) {
		return "error";
	}

}
