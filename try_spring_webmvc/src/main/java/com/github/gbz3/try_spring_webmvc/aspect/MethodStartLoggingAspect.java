package com.github.gbz3.try_spring_webmvc.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodStartLoggingAspect {

	@Before( "execution(* *..*Controller.*(..))" )
	public void startLog( JoinPoint jp ) {
		System.out.println( "★メソッド開始: " + jp.getSignature() );
	}
}
