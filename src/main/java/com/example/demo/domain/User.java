package com.example.demo.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class User {

	private final String username;
	private final String password;
	private final List<GrantedAuthority> grantedAuthorities;
	private final boolean accountNotExpired;
	private final boolean accountNotLocked;
	private final boolean credentialsNonExpired;
	private final boolean enabled;

	public User(String username, String password, List<GrantedAuthority> grantedAuthorities, boolean accountNotExpired, boolean accountNotLocked, boolean credentialsNonExpired, boolean enabled) {
		this.username = username;
		this.password = password;
		this.grantedAuthorities = grantedAuthorities;
		this.accountNotExpired = accountNotExpired;
		this.accountNotLocked = accountNotLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public List<GrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	public boolean isAccountNotExpired() {
		return accountNotExpired;
	}

	public boolean isAccountNotLocked() {
		return accountNotLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}
}
