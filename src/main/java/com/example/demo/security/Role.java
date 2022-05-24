package com.example.demo.security;
import java.util.List;

import static com.example.demo.security.Authority.*;

public enum Role {
	ADMIN(List.of(READ_STUDENT, WRITE_STUDENT)),
	STUDENT(List.of()),
	TRAINEE(List.of(READ_STUDENT));

	private final List<Authority> authorities;

	Role(List<Authority> authorities) {
		this.authorities = authorities;
	}
}
