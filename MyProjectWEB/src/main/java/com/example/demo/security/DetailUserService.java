
package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repositories.UserRepository;

import model.User;

@Service
public class DetailUserService implements UserDetailsService {

	@Autowired
	UserRepository uRep;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User u = uRep.findByUserEmail(email);
		UserDetails ud = new CustomUserDetail(u);
		return ud;
	}

}
