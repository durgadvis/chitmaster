package com.chitmaster.controllers;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.chitmaster.dto.UserDto;
import com.chitmaster.entity.Register;
import com.chitmaster.security.ChitMasterJwtAuthenticationProvider;
import com.chitmaster.security.ChitMasterJwtTokenUtil;
import com.chitmaster.security.ChitMasterJwtUser;
import com.chitmaster.service.LoginService;

@RestController
public class LoginController {

	@Inject
	private ChitMasterJwtAuthenticationProvider authenticationManager;

	@Inject
	private ChitMasterJwtTokenUtil jwtTokenUtil;

	@Inject
	private LoginService loginService;

	@GetMapping("/login")
	public ModelAndView showLoginForm() {
		ModelAndView modelAndView = new ModelAndView("login");
		modelAndView.addObject("welcomeMessage", "Hi User, please login to continue");
		
		return modelAndView;
	}
	
	@PostMapping("/login")
	public @ResponseBody ChitMasterJwtUser verifyLogin(@RequestBody UserDto userDto, HttpServletResponse response) throws IOException {
		
		ChitMasterJwtUser user=null;

		if (!loginService.isUserCredentialValid( userDto.getUsername(), userDto.getPassword())) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		}
		
		try {
			 user = new ChitMasterJwtUser(userDto.getUsername(), userDto.getPassword());
			jwtTokenUtil.createAuthoritiesForUser(user);
			final String generatedToken = jwtTokenUtil.generateTokenForUser(user);
			user.setToken(generatedToken);
			
			final Authentication authentication = authenticationManager.authenticate(jwtTokenUtil.createJwtAuthToken(user));
	        SecurityContextHolder.getContext().setAuthentication(authentication);

			
		} catch (Exception exception) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		}
		return user;
	}

	@GetMapping("/signup")
	public ModelAndView showSignupForm() {
		ModelAndView modelAndView = new ModelAndView("signup");
		modelAndView.addObject("welcomeMessage", "Hi User, please signup to continue");
		
		return modelAndView;
	}
	
	@PostMapping("/signup")
	public ModelAndView createUserOnSignup(@ModelAttribute Register user) throws IOException {
		loginService.addUser(user);
		return new ModelAndView("redirect:login");
	}
	
}
