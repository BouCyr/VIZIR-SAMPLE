package com.cbo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity()
public class SecurityConfig {

	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
            .and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
    }

	public void configure(HttpSecurity http) throws Exception {
		System.err.println("CONFIGURING HTTP");
		http
			.authorizeRequests()
				.anyRequest().anonymous()
			.and()
				.httpBasic();
	}
}
