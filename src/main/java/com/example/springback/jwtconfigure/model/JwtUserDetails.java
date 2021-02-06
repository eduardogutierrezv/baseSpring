package com.example.springback.jwtconfigure.model;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUserDetails implements UserDetails {

	private String username;
	private String passwd;
	private String token;
	private Collection<? extends GrantedAuthority> grantedAuthorities;

	public JwtUserDetails(String username, String passwd, String token, List<GrantedAuthority> grantedAuthorities) {

		this.username = username;
		this.passwd = passwd;
		this.token = token;
		this.grantedAuthorities = grantedAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getToken() {
		return token;
	}

	public Collection<? extends GrantedAuthority> getGetAuthorities() {
		return grantedAuthorities;
	}

}
