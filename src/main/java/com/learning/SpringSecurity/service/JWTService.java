package com.learning.SpringSecurity.service;

import com.learning.SpringSecurity.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTService {

    public String generateToken(User user) {
        Map<String, Object> clames = new HashMap<>();
        return Jwts
                .builder()
                .issuer("SpringSecurityProject")
                .subject(user.getUserName())
                .claims()
                .add(clames)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 10*60*1000))
                .and()
                .signWith(this.generateSecretKey())
                .compact();

    }

    private SecretKey generateSecretKey(){
        byte[] decode = Decoders.BASE64.decode(this.getSecretKey());
        return Keys.hmacShaKeyFor(decode);
    }

    private String getSecretKey() {
        return "12d304351462ad278a53a40c2bb26bc5852048c3e59c88d65ab60c2c7f89b3f0";
    }

    public String extractUserName(String token) {
        Claims payload = getClaims(token);
        return payload.getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(generateSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokeValid(String jwtToken, UserDetails userDetails) {
        Claims claims = getClaims(jwtToken);
        String subject = claims.getSubject();
        return subject.equals(userDetails.getUsername()) &&
                !claims.getExpiration().before(new Date());
    }
}
