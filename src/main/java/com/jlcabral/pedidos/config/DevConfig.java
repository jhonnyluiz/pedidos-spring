package com.jlcabral.pedidos.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jlcabral.pedidos.services.DBService;

/**
 * @author Jhonny Cabral
 * @date 22 de mar de 2018
 */
@Configuration
@Profile("dev")
public class DevConfig {

	private static final String STRATEGY_CREATE = "create";

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if (strategy.equals(STRATEGY_CREATE)) {
			dbService.instantiateTestDatabase();
			return true;
		}
		return false;
	}
}
