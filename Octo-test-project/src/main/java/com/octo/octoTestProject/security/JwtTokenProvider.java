package com.octo.octoTestProject.security;

import com.octo.octoTestProject.model.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {


    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(User user) {
        Date durationTimeLife = new Date(new Date().getTime() + jwtExpirationInMs);
        return Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .claim("roles", user.getRoles())
                .claim("phoneNumber", user.getPhoneNumber())
                .claim("email", user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(durationTimeLife)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .setSubject(username)
                .compact();
    }

    public String getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
