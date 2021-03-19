package com.bingo.config.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (!username.equals("zhangsan")) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		User user = new User();
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		user.setPassword("$2a$10$isgrL67QrVjGJkNxfwRjfOMSGtQkecQTAmuV259mGV07rGdKWwFvy");
		user.setUsername("zhangsan");
		Role role = new Role();
		// 角色必须ROLE_开头
		role.setName("ROLE_USER");
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user.setRoles(roles);

		return user;
	}

}
