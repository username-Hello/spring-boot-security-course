package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demo.security.Authority.READ_STUDENT;
import static com.example.demo.security.Authority.WRITE_STUDENT;
import static com.example.demo.security.Role.ADMIN;
import static com.example.demo.security.Role.STUDENT;
import static com.example.demo.security.Role.TRAINEE;

@Configuration
@EnableWebSecurity
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
				.antMatchers(HttpMethod.GET, "/management/**").hasAnyAuthority(WRITE_STUDENT.getAuthority(), READ_STUDENT.getAuthority())
				.antMatchers(HttpMethod.DELETE, "/management/**").hasAuthority(WRITE_STUDENT.getAuthority())
				.antMatchers(HttpMethod.PUT, "/management/**").hasRole(WRITE_STUDENT.getAuthority())
				.antMatchers(HttpMethod.POST, "/management/**").hasRole(WRITE_STUDENT.getAuthority())
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
