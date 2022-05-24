package com.example.demo.security;

import com.example.demo.student.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

import static com.example.demo.security.Authority.WRITE_STUDENT;
import static com.example.demo.security.Role.ADMIN;
import static com.example.demo.security.Role.STUDENT;
import static com.example.demo.security.Role.TRAINEE;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;

	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/", "index", "/css/*", "/js/*").permitAll()
				.antMatchers("/api/**").hasAnyRole(STUDENT.name(), ADMIN.name())
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails annaSmith = User.builder()
				.username("annasmith")
				.password(passwordEncoder.encode("123"))
				.roles(ADMIN.name())
				.build();

		UserDetails lina = User.builder()
				.username("linda")
				.password(passwordEncoder.encode("123"))
				.roles(STUDENT.name())
				.build();

		UserDetails tom = User.builder()
				.username("tom")
				.password(passwordEncoder.encode("123"))
				.roles(TRAINEE.name())
				.build();

		return new InMemoryUserDetailsManager(List.of(annaSmith, lina, tom));
	}
}
