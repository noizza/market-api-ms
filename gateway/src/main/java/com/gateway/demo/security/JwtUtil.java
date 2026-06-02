package com.gateway.demo.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private final String KEY_MASTER = "asdasd";

    private SecretKey getKey() {
        byte[] keyBytes = KEY_MASTER.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) {
        return io.jsonwebtoken.Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token) {
        try {
         return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}
