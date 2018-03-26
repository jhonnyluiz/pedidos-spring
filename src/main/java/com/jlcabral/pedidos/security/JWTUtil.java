package com.jlcabral.pedidos.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jlcabral.pedidos.architecture.UtilObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Jhonny Cabral
 * @date 26 de mar de 2018
 */
@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String username) {
		return Jwts.builder()
					.setSubject(username)
					.setExpiration(new Date(System.currentTimeMillis() + expiration))
					.signWith(SignatureAlgorithm.HS512, secret.getBytes())
					.compact();
	}

	public boolean tokenValido(String token) {
		String username = getUsername(token);
		Date expirationDate = getExpirationDate(token);
		Date now = new Date(System.currentTimeMillis());
		if(UtilObject.isNotNull(username) && UtilObject.isNotNull(expirationDate) && now.before(expirationDate)) {
			return true;
		}
		return false;
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if(UtilObject.isNotNull(claims)) {
			return claims.getSubject();
		}
		return null;
	}
	
	public Date getExpirationDate(String token) {
		Claims claims = getClaims(token);
		if(UtilObject.isNotNull(claims)) {
			return claims.getExpiration();
		}
		return null;
	}

	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}
}
