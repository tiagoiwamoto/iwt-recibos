package br.com.tiagoiwamoto.iwtrecibos.core.service.auth;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 13/03/2022 | 21:05
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.signin.key}")
    private String SIGNIN_KEY;
    @Value("${jwt.refresh.signin.key}")
    private String REFRESH_SIGNIN_KEY;
    @Value("${jwt.expiration.time}")
    private Integer EXPIRATION_TIME;
    @Value("${jwt.refresh.expiration.time}")
    private Integer REFRESH_EXPIRATION_TIME;

    public String generateToken(Authentication authentication){
        return generateToken(SIGNIN_KEY, authentication.getName(), EXPIRATION_TIME);
    }

    public String generateRefreshToken(String username){
        return generateToken(REFRESH_SIGNIN_KEY, username, REFRESH_EXPIRATION_TIME);
    }

    public LocalDateTime getExpirationFromToken(String token){
        Claims claims = this.getClaims(token, SIGNIN_KEY);
        return claims.getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = this.getClaims(token, SIGNIN_KEY);
        return claims.getSubject();
    }

    public String getUserFromRefreshToken(String refreshToken){
        Claims claims = this.getClaims(refreshToken, REFRESH_SIGNIN_KEY);
        return claims.getSubject();
    }

    private Claims getClaims(String token, String signinKey){
        return Jwts.parser().setSigningKey(signinKey).parseClaimsJws(token).getBody();
    }

    private String generateToken(String signinKey, String subject, int expirationTime) {
        Map<String, Object> claims = new HashMap<>();
        Instant currentDateTime = Instant.now();
        Instant expirationDateTime = currentDateTime.plusSeconds(expirationTime);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(currentDateTime.toEpochMilli()))
                .setExpiration(new Date(expirationDateTime.toEpochMilli()))
                .signWith(SignatureAlgorithm.HS512, signinKey)
                .compact();
    }

}
