package com.example.demo.AppServices;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;
import com.example.demo.Users.User.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@Service
public class JwtService {

    SecretKey key = Jwts.SIG.HS256.key().build();
    String SECRET = Base64.getEncoder().encodeToString(key.getEncoded());
    
     private Key getSignInKey() {
       return Keys.hmacShaKeyFor(java.util.Base64.getDecoder().decode(SECRET));
     }

    public String generateToken(String email , Role role , long userId){
        return Jwts.builder()
                   .subject(email)
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis() + 1000 *60 *60 *24) )
                   .claim("role", role)
                   .claim("userId", userId)
                   .signWith(getSignInKey())
                   .compact();
    }

    
}