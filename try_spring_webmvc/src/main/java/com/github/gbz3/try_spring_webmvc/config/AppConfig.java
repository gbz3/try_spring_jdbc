package com.github.gbz3.try_spring_webmvc.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SuppressWarnings("deprecation")
@Configuration
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement
@MapperScan("com.github.gbz3.authlib.domain.mapper")
@Import({WebApiSecurityConfig.class, WebSecurityConfig.class})
public class AppConfig {

	@Bean(destroyMethod = "close")
	public DataSource dataSource(
			@Value("${database.driverClassName}") String driverClassName,
			@Value("${database.url}") String url,
			@Value("${database.username}") String username,
			@Value("${database.password}") String password,
			@Value("${cp.maxTotal}") int maxTotal,
			@Value("${cp.maxIdle}") int maxIdle,
			@Value("${cp.minIdle}") int minIdle,
			@Value("${cp.maxWaitMillis}") long maxWaitMillis) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setMaxTotal(maxTotal);
		dataSource.setMaxIdle(maxIdle);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxWaitMillis(maxWaitMillis);
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));
		return sessionFactoryBean;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
