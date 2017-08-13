package com.chitmaster.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class ChitMasterJwtTokenUtil {

	@Value("${jwt.secret}")
	private String secret;

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public void createAuthoritiesForUser(ChitMasterJwtUser user) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("Admin"));
		user.setAuthorities(authorities);
	}

	public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

	private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

	/**
	 * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
	 * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
	 * 
	 * @param token the JWT token to parse
	 * @return the User object extracted from specified token or null if a token is invalid.
	 */
	@SuppressWarnings("unchecked")
	public ChitMasterJwtUser parseToken(String token) {
		try {
			Claims body = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();

			ChitMasterJwtUser user = new ChitMasterJwtUser();
			user.setUsername(body.getSubject());
			user.setEmail((String)body.get("email"));

			System.out.println("Getting user " + user);
			System.out.println("Getting user name " + user.getUsername());
			System.out.println("Getting user email " + user.getEmail());
			return user;

		} catch (JwtException e) {
			System.out.println("Getting a JwtException while parsing token " + e);
			return null;
		} catch (ClassCastException e) {
			System.out.println("Getting ClassCastexception while parsing token " + e);
			return null;
		}
	}

	/**
	 * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
	 * User object. Tokens validity is infinite.
	 * 
	 * @param u the user for which the token will be generated
	 * @return the JWT token
	 */
	public String generateTokenForUser(ChitMasterJwtUser user) {
		Date expirationDate = DateUtils.addMonths(new Date(), 1);

		Claims claims = Jwts.claims().setSubject(user.getUsername());
		claims.put("email", user.getEmail());

		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret)
				.setIssuer("http://chitmaster/")
				.setExpiration(expirationDate)
				.compact();
	}

	public Authentication createJwtAuthToken(ChitMasterJwtUser user) {
		JwtAuthenticationToken token = new JwtAuthenticationToken(user.getAuthorities(), user.getEmail(), user.getUsername(), user.getToken());
		return token;
	}
}
