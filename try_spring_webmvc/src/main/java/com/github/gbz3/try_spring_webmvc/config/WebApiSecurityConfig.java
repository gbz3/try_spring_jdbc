package com.github.gbz3.try_spring_webmvc.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity
@ComponentScan({"com.github.gbz3.authlib.domain"})
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebApiSecurityConfig extends WebSecurityConfigurerAdapter {

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
	}

	@Autowired
	AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	AccessDeniedHandler accessDeniedHandler;

	/**
	 * Http通信のセキュリティ設定
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/api/**")
			.exceptionHandling()
//			.defaultAuthenticationEntryPointFor( authenticationEntryPoint, new AntPathRequestMatcher( "/api/**" ) );
//			.authenticationEntryPoint( authenticationEntryPoint )
			.accessDeniedHandler( accessDeniedHandler )
			;

		http.authorizeRequests().anyRequest().authenticated();

		http.httpBasic().authenticationEntryPoint( authenticationEntryPoint );
		http.csrf().disable();

	}

	/**
	 * <p>未認証エラー</p>
	 * @return
	 */
	@Bean
	AuthenticationEntryPoint authenticationEntryPoint() {
		return new AuthenticationEntryPoint() {
			@Override
			public void commence(
					HttpServletRequest request,
					HttpServletResponse response,
					AuthenticationException authException ) throws IOException, ServletException {
				response.setStatus( HttpServletResponse.SC_UNAUTHORIZED );
				response.setContentType( MediaType.APPLICATION_JSON_VALUE );
				response.setCharacterEncoding( StandardCharsets.UTF_8.displayName() );
				response.getWriter()
					.println( "{\"errorMessage\": \"" + authException.getLocalizedMessage() + "\"}" );
			}
		};
	}

	/**
	 * <p>認可エラー</p>
	 * @return
	 */
	@Bean
	AccessDeniedHandler accessDeniedHandler() {
		return new AccessDeniedHandler() {
			@Override
			public void handle(
					HttpServletRequest request,
					HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				response.setStatus( HttpServletResponse.SC_FORBIDDEN );
				response.setContentType( MediaType.APPLICATION_JSON_VALUE );
				response.setCharacterEncoding( StandardCharsets.UTF_8.displayName() );
				response.getWriter()
					.println( "{\"errorMessage\": \"" + accessDeniedException.getLocalizedMessage() + "\"}" );
			}
		};
	}

}
