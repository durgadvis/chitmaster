package com.chitmaster.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class ChitMasterJwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private ChitMasterJwtTokenUtil jwtTokenUtil;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    	// Can add user token expiration checks here.
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    	String token = null;
    	
    	if (authentication instanceof JwtAuthenticationToken) {
    		System.out.println("Instance of JwtAuthenticationToken");
    		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
    		token = jwtAuthenticationToken.getToken();
    	}

    	System.out.println("Token obtained is " + token);
    	System.out.println(authentication.toString() + " " + authentication.getName() + " " + authentication.getPrincipal() + " " + authentication.getCredentials());
        ChitMasterJwtUser parsedUser = jwtTokenUtil.parseToken(token == null? authentication.toString() : token);

        if (parsedUser == null) {
            throw new JwtTokenMalformedException("JWT token is not valid");
        }
        
        return parsedUser;
    }

}