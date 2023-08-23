package com.example.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {
		
		http
		.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/css/**","/js/**","/img/**","/images/**","/webfonts/**").permitAll() 
				.requestMatchers("/","/member/**","/book/**").permitAll()
				.requestMatchers("/favicon.ico","/error" , "/phoneCheck" , "/delete/**").permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				
				.anyRequest().authenticated()
				)
		.formLogin(formLogin -> formLogin
				.loginPage("/member/login") //로그인 페이지
				.defaultSuccessUrl("/") //로그인 성공시 페이지
				.usernameParameter("email")
				
				.failureUrl("/member/login/error")
				)
		.logout(logout -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.logoutSuccessUrl("/")
		)
		
		.exceptionHandling(handling -> handling
				.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
				)
		.rememberMe(Customizer.withDefaults());
	
		
		return http.build();
		
	}
	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
