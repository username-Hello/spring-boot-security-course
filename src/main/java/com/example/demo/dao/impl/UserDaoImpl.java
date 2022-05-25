package com.example.demo.dao.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import com.google.common.collect.Lists;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.Role.ADMIN;
import static com.example.demo.security.Role.STUDENT;
import static com.example.demo.security.Role.TRAINEE;

@Repository
public class UserDaoImpl implements UserDao {

	private final PasswordEncoder passwordEncoder;

	public UserDaoImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return getUsers()
				.stream()
				.filter(user -> username.equals(user.getUsername()))
				.findFirst();
	}

	private List<User> getUsers() {
		return Lists.newArrayList(
				new User(
						"annasmith",
						passwordEncoder.encode("password"),
						STUDENT.getGrantedAuthorities(),
						true,
						true,
						true,
						true
				),
				new User(
						"linda",
						passwordEncoder.encode("password"),
						ADMIN.getGrantedAuthorities(),
						true,
						true,
						true,
						true
				),
				new User(
						"tom",
						passwordEncoder.encode("password"),
						TRAINEE.getGrantedAuthorities(),
						true,
						true,
						true,
						true
				)
		);
	}
}
