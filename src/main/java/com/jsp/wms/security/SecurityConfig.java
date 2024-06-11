package com.jsp.wms.security;
//3rd
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean //its goint to crete object of return type of this method
	 PasswordEncoder passWordEncoder() {
		
		return new BCryptPasswordEncoder(12);//12 how many iterations the password has to get hashed
/*whenever i want to encode password before db ,i can autowire this PasswordEncoder in my Mapper class and invoke this method
aim 1)encode password before db
2)after getting password from db i must vslidate encrypted pass ,and i must tell 
spring which type of algorithm i have used hence use @ Bean   */
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception { //will help reset whole configuratn
		return httpSecurity.csrf(csrf->csrf.disable()) //coz we are not working with csrf certificatn
				//httpSecurity ref, will help build security builder chain 
				//@Bean over the method
				.authorizeHttpRequests(authorize->authorize.requestMatchers("/api/v1/register") //requestMatchers to configure the url endpoint ie.private or public)
									//authorize is ref name   		'regiter' endpointis public coz	without registering how can superadmin login
						.permitAll()// ,to permit all users who try to hit that particular url hence make it public and permit to all
						.anyRequest() //if any other request comes ,it must be authenticated
						.authenticated()
						)
				.formLogin(Customizer.withDefaults())//is session based configuration by setting default appln 
				.build();
		
	}
	//authorizeHttpRequests() takes Customizer functional interface as hte parameter which inturn takes 1 value but doen not return any value
}
//HttpSecurity httpSecurity will help me builf security filetr chain