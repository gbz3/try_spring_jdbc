package com.github.gbz3.try_spring_webmvc.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiController {

	// curl -sv -u dummy:password http://localhost:8080/try_spring_webmvc/api/get
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public String get() {
		return "Dummy.";
	}

}
