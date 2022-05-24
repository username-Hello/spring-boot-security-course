package com.example.demo.security;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.security.Authority.*;

public enum Role {
	ADMIN(List.of(READ_STUDENT, WRITE_STUDENT)),
	STUDENT(List.of()),
	TRAINEE(List.of(READ_STUDENT));

	private final List<Authority> authorities;

	Role(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public List<GrantedAuthority> getGrantedAuthorities() {
		List<GrantedAuthority> grantedAuthorities = authorities
				.stream()
				.map(authority -> new SimpleGrantedAuthority("ROLE_" + authority.getAuthority()))
				.collect(Collectors.toList());
		grantedAuthorities.add(new SimpleGrantedAuthority(this.name()));
		return grantedAuthorities;
	}
}
