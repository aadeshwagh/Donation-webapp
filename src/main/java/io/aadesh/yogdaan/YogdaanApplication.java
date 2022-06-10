package io.aadesh.yogdaan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationCodeGrantFilter;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;

@SpringBootApplication
@Configuration
public class YogdaanApplication extends WebSecurityConfigurerAdapter {

//	@Autowired
//	RegistrationFilter registrationFilter;
	public static void main(String[] args) {
		SpringApplication.run(YogdaanApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/").permitAll()
				.anyRequest().authenticated()
				.and()
				.csrf().disable()
				.oauth2Login()
				.loginPage("/login");

	}


}
