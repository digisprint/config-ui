package com.liverpool.configuration.config;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.liverpool.configuration.beans.User;
import com.liverpool.configuration.properties.ConfigrationsProeprties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable{

	private ConfigrationsProeprties configProps;
	
	private static final long serialVersionUID = -2550185165626007488L;
	
	public static final long JWT_TOKEN_VALIDITY = 30 * 60;
	
	public JwtTokenUtil(ConfigrationsProeprties configProps) {
		this.configProps = configProps;
	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(configProps.getSecretKey()).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(User user) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, user);
	}

	private String doGenerateToken(Map<String, Object> claims, User user) {
				
		return Jwts.builder().setIssuer("CongfigServer").setSubject(user.getUserName())
				.claim("userName", user.getUserName()).claim("roles", user.getRoles())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, configProps.getSecretKey()).compact();
	}
	
	public Boolean validateToken(String token, User userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUserName()) && !isTokenExpired(token));
	}
}
