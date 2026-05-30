package com.healfiness.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRET_KEY;

    private final long ACCESS_EXPIRATION; // 1 hour in milliseconds

    private final long REFRESH_EXPIRATION; // 7 days in milliseconds

    public JwtService(
            @Value("${security.jwt.secret}") String secretKey,
            @Value("${security.jwt.expiration-millis}") long accessExpiration,
            @Value("${security.jwt.refresh-expiration-millis}") long refreshExpiration
    ) {
        SECRET_KEY = secretKey;
        ACCESS_EXPIRATION = accessExpiration;
        REFRESH_EXPIRATION = refreshExpiration;
    }

    public String generateToken(Long expiration, TOKEN_TYPE tokenType, CustomUserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("usersId", user.getUsersId())
                .claim("roles", user.getAuthorities())
                .claim("tokenType", tokenType)
                .setExpiration(new java.util.Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver
    ) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }

    public boolean isValidToken(String token) {
        return extractClaim(token, Claims::getExpiration).after(new java.util.Date());
    }

    public String generateAccessToken(CustomUserDetails user) {
        return generateToken(ACCESS_EXPIRATION, TOKEN_TYPE.ACCESS, user);

    }

    public String generateRefreshToken(CustomUserDetails user) {
        return generateToken(REFRESH_EXPIRATION, TOKEN_TYPE.REFRESH, user);
    }

    private enum TOKEN_TYPE {
        ACCESS,
        REFRESH
    }

}
