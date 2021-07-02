package com.liverpool.configuration.filter;

import java.io.IOException;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liverpool.configuration.properties.ConfigrationsProeprties;
import com.liverpool.configuration.service.UserService;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;

@Component
public class JwtValidationFilter extends OncePerRequestFilter {

	@Autowired
	private ConfigrationsProeprties properties;

	@Autowired
	private UserService userService;

	@Override
	protected void initFilterBean() throws ServletException {
		userService.insertInitialData();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = request.getHeader("token");
		if (token != null) {
			SignatureAlgorithm sa = SignatureAlgorithm.HS512;
			SecretKeySpec secretKeySpec = new SecretKeySpec(properties.getSecretKey().getBytes(), sa.getJcaName());
			DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, secretKeySpec);
			String[] chunks = token.split("\\.");
			if (chunks.length < 2) {
				Exception e = new Exception("seems you doesnt have proper permissions");
				ObjectMapper mapper = new ObjectMapper();
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				response.getWriter().write(mapper.writeValueAsString(e));
			} else {
				String tokenWithoutSignature = chunks[0] + "." + chunks[1];
				String signature = chunks[2];
				if (validator.isValid(tokenWithoutSignature, signature) && !request.getRequestURI().equals("/login")) {
					doFilter(request, response, filterChain);
				} else {
					Exception e = new Exception("User is already loggedIn");
					ObjectMapper mapper = new ObjectMapper();
					response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
					response.getWriter().write(mapper.writeValueAsString(e));
				}
			}
		}else if (request.getRequestURI().equals("/login") || request.getRequestURI().equals("/register")) {
			doFilter(request, response, filterChain);
		} else {
			Exception e = new Exception("Not allowed to use the Service");
			ObjectMapper mapper = new ObjectMapper();
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().write(mapper.writeValueAsString(e));
		}
	}
}