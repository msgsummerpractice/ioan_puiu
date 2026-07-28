package com.example.DBdemo.config.security;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    private String jwtSecret = "dvjS7T4VfcxdQ/gd3r+951eo/oK7mcm3K0nMMq37rbo=";
    private long jwtExpirationInMs = 3600000; //1h = 3600s and 3600*1000 = 3600000 milliseconds

     public String generateToken(Authentication authentication) {
        String name = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);
 
        String token = Jwts.builder()
                .subject(name)
                .issuedAt(currentDate)
                .expiration(expireDate)
                .signWith((SecretKey) key())
                .compact();
        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // extract username from JWT token
    public String getUsername(String token){

        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // validate JWT token
    public boolean validateToken(String token){
        Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parse(token);
        return true;

    }

}
