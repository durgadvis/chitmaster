package com.chitmaster.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ChitMasterJwtUser implements UserDetails {

	private static final long serialVersionUID = 1455167962335555187L;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
    private String token;

	public ChitMasterJwtUser() {    	
    }

    public ChitMasterJwtUser(
          String username, String password
    ) {
        this.username = username;
        this.password = password;
       
    }

	public void setUsername(String username) {
		this.username = username;
	}

	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

    public String getUsername() {
        return username;
    }

    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
   
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@JsonIgnore
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

}