package com.github.gbz3.try_spring_webmvc.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.github.gbz3.try_spring_webmvc.api")
public class ApiControllerAdvice {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> handleException(HttpServletRequest req, Exception e) {
		return new ResponseEntity<String>( "error at api.", HttpStatus.BAD_REQUEST );
	}

}
