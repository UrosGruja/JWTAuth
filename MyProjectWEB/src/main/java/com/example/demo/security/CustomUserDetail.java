
package com.example.demo.security;

import java.util.Collection;
import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import model.User;

public class CustomUserDetail implements UserDetails {


	private static final long serialVersionUID = 1L;
	
	private User u;

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public CustomUserDetail(User u) {
		this.u = u;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		return authorities;
	}

	@Override
	public String getPassword() {
		return u.getPassword();
	}

	@Override
	public String getUsername() {
		return u.getEmail();
	}

	public String getName() {
		return u.getName();
	}

	@Override public boolean isAccountNonExpired() {
		return true; 	
	}

	@Override public boolean isAccountNonLocked() {
		return true;	
	}

	@Override public boolean isCredentialsNonExpired() {
		return true;	
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
