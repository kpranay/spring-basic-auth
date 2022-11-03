package com.argano.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DemoAppSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private MyBasicAuthenticationEntryPoint authEntryPoint;
	
	private static final String[] AUTH_WHITELIST = {
	
			};
	private static final String API_PATTERN = "/api/**";

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
          .withUser("user")
          .password(encoder.encode("password"))
          .roles("USER")
          .and()
          .withUser("admin")
          .password(encoder.encode("password"))
          .roles("ADMIN")
          .and()
          .withUser("user2")
          .password(encoder.encode("password"))
          .roles("someother-role");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {        
        http
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, API_PATTERN).permitAll()
            
            .antMatchers(AUTH_WHITELIST).permitAll()
            
            .antMatchers(API_PATTERN).authenticated()
            .antMatchers("/**").denyAll()
            .and()
            .httpBasic()
            .and()
            .cors()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(authEntryPoint)
            .and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);      

        http.csrf().disable();
    }
}
