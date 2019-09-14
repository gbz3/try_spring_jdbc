package com.github.gbz3.try_spring_webmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvc
@ComponentScan({"com.github.gbz3.try_spring_webmvc.api", "com.github.gbz3.try_spring_webmvc.app", "com.github.gbz3.try_spring_webmvc.aspect"})
@EnableAspectJAutoProxy
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp();
	}

}
