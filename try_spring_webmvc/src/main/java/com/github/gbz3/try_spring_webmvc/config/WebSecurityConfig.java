package com.github.gbz3.try_spring_webmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@ComponentScan({"com.github.gbz3.authlib.domain"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	void configureAuthenticationManager( AuthenticationManagerBuilder auth ) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	/**
	 * Web全般のセキュリティ設定
	 */
	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
			.antMatchers( "/resources/**" );
	}

	/**
	 * Http通信のセキュリティ設定
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// セキュリティで管理されたページの設定
		http.authorizeRequests()
			.antMatchers( "/**" )
			.authenticated();

		http.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl( "/top" )
			.failureUrl( "/login?error" )
			.permitAll();
//		http.authorizeRequests()
//			.anyRequest().authenticated();
		http.logout()
			.permitAll();
	}

}
