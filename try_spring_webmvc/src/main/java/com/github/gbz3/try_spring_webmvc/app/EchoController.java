package com.github.gbz3.try_spring_webmvc.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
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

	private static final Logger logger = LoggerFactory.getLogger( EchoController.class );

	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(method = RequestMethod.GET)
	public String viewInput(Model model) {
		EchoForm form = new EchoForm();
		model.addAttribute(form);
		return "echo/input";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String echo(@Validated EchoForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "echo/input";
			//throw new IllegalStateException("dummy.");
		}
		logger.info( "OK?" );
		try {
			String pwd = jdbcTemplate.queryForObject(
					"SELECT password FROM my_user WHERE mail = ?",
					String.class, form.getText() );
			logger.info( "pwd: [" + pwd + "]" );
		} catch (DataAccessException e) {
			logger.error(e.getLocalizedMessage(), e);
		}

		return "echo/output";
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleException(Exception e) {
		return "error";
	}

}
