package com.soromiso.jwtex.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String SECRET_KEY;

    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long jwtRefreshExpiration;

    // MARK: - Token Validation checking
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // MARK: - Token Generation
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    // MARK: - Refresh Token Generation
    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(new HashMap<>(), userDetails, jwtRefreshExpiration);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long jwtExpiration) {
        return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
    }

    // MARK: - Token Extraction
    public String extractUsername(String token) {
        try {
            var returnExtractClaim =  extractClaim(token, Claims::getSubject);
            System.out.println("🍎 57: returnExtractClaim: " + returnExtractClaim);
            return returnExtractClaim;
        } catch (Exception e) {
            System.out.println("🍎 60: extractUsername error: " + e.getMessage());
            return null;
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = extractAllClaims(token);           
            System.out.println("🍎 61: claims: " + claims); 
            return claimsResolver.apply(claims);
            
        } catch (Exception e) {
            System.out.println("🍎 65: extractClaim error: " + e.getMessage());
            return null;
        }
    }

    private Claims extractAllClaims(String token) {
        System.out.println("🍎 71: extractAllClaims token: " + token);
        try {
            var returnClaims = Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    
            System.out.println("🍎 79: returnClaims: " + returnClaims);
            return returnClaims;
            
        } catch (Exception e) {
            System.out.println("🍎 83: returnClaims error: " + e.getMessage());
            return null;
        }
    }

    private Key getSignInKey() {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // SECRET_KEY를 디코딩합니다.
            var returnKey = Keys.hmacShaKeyFor(keyBytes);
            System.out.println("🍎 92: returnKey: " + returnKey);
            return returnKey;
            
        } catch (Exception e) {
            System.out.println("🍎 96: returnKey error: " + e.getMessage());
            return null;
        }
    }
    
    
    
}
