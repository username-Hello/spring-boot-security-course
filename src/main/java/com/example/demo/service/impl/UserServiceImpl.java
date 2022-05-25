package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import com.example.demo.security.principal.UserPrincipal;
import com.example.demo.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return createPrincipal(userDao
				.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found")));
	}

	protected UserPrincipal createPrincipal(User user) {
		return new UserPrincipal(user);
	}
}
