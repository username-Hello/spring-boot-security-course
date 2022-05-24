package com.example.demo.security;

public enum Authority {
	READ_STUDENT("student:read"),
	WRITE_STUDENT("student:write");

	private final String authority;

	Authority(String name) {
		this.authority = name;
	}

	public String getAuthority() {
		return authority;
	}
}
