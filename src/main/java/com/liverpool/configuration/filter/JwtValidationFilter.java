package com.liverpool.configuration.filter;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liverpool.configuration.beans.User;
import com.liverpool.configuration.config.JwtTokenUtil;
import com.liverpool.configuration.properties.ConfigrationsProeprties;
import com.liverpool.configuration.repository.UserRepository;
import com.liverpool.configuration.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtValidationFilter extends OncePerRequestFilter {

	private UserService userService;

	private JwtTokenUtil jwtUtils;
	
	private UserRepository userRepo;
	
	private ConfigrationsProeprties properties;

	public JwtValidationFilter(UserService userService, JwtTokenUtil jwtUtils, UserRepository userRepo,
			ConfigrationsProeprties properties) {
		this.userService = userService;
		this.jwtUtils = jwtUtils;
		this.userRepo = userRepo;
		this.properties = properties;
	}

	@Override
	protected void initFilterBean() throws ServletException {
		userService.insertInitialData();
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		doFilter(request, response, filterChain);
		/*
		log.debug("-----------------HEADERS FOR THIS REQUEST -------------");
		Collections.list(request.getHeaderNames()).forEach(item -> log.debug("header name is:::"+item));
		log.debug(request.getHeader("access-control-request-headers"));
		String token = request.getHeader("token");
		ObjectMapper mapper = new ObjectMapper();
		if (!request.getRequestURI().contains("/config/")) {
			doFilter(request, response, filterChain);
		} else if (token != null) {
			String[] chunks = token.split("\\.");
			if (chunks.length < 2) {
				log.error("TOKEN CHUNKS INVALID :::{}",token);
				Exception e = new Exception("seems you doesnt have proper permissions");
				response.setStatus(HttpStatus.FORBIDDEN.value());
				response.getWriter().write(mapper.writeValueAsString(e));
			} else {
				try {
					String usernameFromToken = jwtUtils.getUsernameFromToken(token);
					User user = userRepo.findByUserName(usernameFromToken);
					if (user != null && jwtUtils.validateToken(token, user) 
							&& !request.getRequestURI().equals("/login")) {
						doFilter(request, response, filterChain);
					} else {
						log.error("TOKEN INVALID WHILE VERIFYING :::{}",token);
						Exception e = new Exception("UNAUTHORIZED USER");
						response.setStatus(HttpStatus.FORBIDDEN.value());
						response.getWriter().write(mapper.writeValueAsString(e));
					}
				} catch(Exception e) {
					log.error("TOKEN INVALID WHILE VERIFYING :::{}",token);
					response.setStatus(HttpStatus.FORBIDDEN.value());
					response.getWriter().write(mapper.writeValueAsString(e));
				}
			}
		} else {
			log.error("TOKEN RECEIVED AS NULL:::{}",token);
			Exception e = new Exception("Not allowed to use the Service");
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.getWriter().write(mapper.writeValueAsString(e));
		} */
	}
}
