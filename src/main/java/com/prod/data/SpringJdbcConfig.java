package com.prod.data;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:DatabaseProperties.properties")
public class SpringJdbcConfig {

	@Value("${db.url}")
	String url;

	@Value("${db.username}")
	String user;

	@Value("${db.driverClassName}")
	String driverClassName;

	@Value("${db.password}")
	String pass;

	@Bean
	public DataSource mysqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(user);
		dataSource.setPassword(pass);

		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
	    return new JdbcTemplate(mysqlDataSource());
	}
}
