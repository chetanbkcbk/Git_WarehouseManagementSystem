package com.jsp.wms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean //its goint to crete object of return type of this method
	 PasswordEncoder passWordEncoder() {
		
		return new BCryptPasswordEncoder(12);
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(authorize->authorize.requestMatchers("/api/v1/register")
						.permitAll()
						.anyRequest()
						.authenticated()
						)
				.formLogin(Customizer.withDefaults())
				.build();
		
	}
	//authorizeHttpRequests() takes Customizer functional interface as hte parameter which inturn takes 1 value but doen not return any value
}
//HttpSecurity httpSecurity will help me builf security filetr chain