package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.concurrent.TimeUnit;

import static com.example.demo.security.Role.ADMIN;
import static com.example.demo.security.Role.STUDENT;
import static com.example.demo.security.Role.TRAINEE;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;

	public SecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
				.antMatchers("/api/**").hasAnyRole(STUDENT.name(), ADMIN.name())
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
				.and()
				.rememberMe()
					.tokenValiditySeconds((int) TimeUnit.DAYS.toMillis(21));
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails annaSmith = User.builder()
				.username("annasmith")
				.password(passwordEncoder.encode("123"))
				.authorities(STUDENT.getGrantedAuthorities())
				.build();

		UserDetails linda = User.builder()
				.username("linda")
				.password(passwordEncoder.encode("123"))
				.authorities(ADMIN.getGrantedAuthorities())
				.build();

		UserDetails tom = User.builder()
				.username("tom")
				.password(passwordEncoder.encode("123"))
				.authorities(TRAINEE.getGrantedAuthorities())
				.build();

		return new InMemoryUserDetailsManager(annaSmith, linda, tom);
	}
}
