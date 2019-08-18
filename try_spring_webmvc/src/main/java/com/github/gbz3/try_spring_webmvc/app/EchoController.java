package com.github.gbz3.try_spring_webmvc.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.github.gbz3.authlib.domain.mapper.AuthUserMapper;
import com.github.gbz3.authlib.domain.model.AuthUser;

@Controller
@RequestMapping("echo")
public class EchoController {

	private static final Logger logger = LoggerFactory.getLogger( EchoController.class );

	@Autowired
	AuthUserMapper authUserMapper;

	@RequestMapping(method = RequestMethod.GET)
	public String viewInput(Model model) {
		EchoForm form = new EchoForm();
		model.addAttribute(form);
		return "echo/input";
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST)
	public String echo(@Validated EchoForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "echo/input";
			//throw new IllegalStateException("dummy.");
		}
		logger.info( "OK?" );
		try {
			AuthUser user = authUserMapper.findOne( form.getText() );
			logger.info( user.toString() );
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
