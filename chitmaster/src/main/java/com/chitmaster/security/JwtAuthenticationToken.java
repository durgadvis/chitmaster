package com.chitmaster.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = -3425157378798788584L;
	private String username;
	private String token;

	public JwtAuthenticationToken(String token) {
		super(null, null, AuthorityUtils.NO_AUTHORITIES);
		this.token = token;
	}

	public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities,String  username, String token) {
		super(username, "N/A", authorities);
		this.token = token;
		this.username = username;
		//setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return "N/A";
	}

	@Override
	public Object getPrincipal() {
		return username;
	}

	public String getToken() {
		return token;
	}

	
}