package com.cdac.ELearning.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.InvalidKeyException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtProvider {

    private Key key;

    @PostConstruct
    public void init() { 	
    	key =Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public Date getExpirationDateFromToken(String token) {
    	return getClaimFromToken(token,Claims::getExpiration);
    }
    
    private Boolean isTokenExpired(String token) {
    	final Date expiration = getExpirationDateFromToken(token);
    	
    	return expiration.before(new Date());
    }
    
    public<T> T getClaimFromToken(String Token,Function<Claims,T>claimsResolver) {
    	Claims claims =getAllClaimsFromToken(Token);
    	return claimsResolver.apply(claims);
    }
    
    private Claims getAllClaimsFromToken(String Token) {
    	return Jwts.parser().setSigningKey(key).parseClaimsJws(Token).getBody();
    }
    
    
    public String generateToken(Authentication authentication) throws InvalidKeyException {
        User principal = (User)authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                .signWith(key)
                .compact();
    }

    
    
    
    
    public boolean validateToken(String jwt) throws SignatureException, ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {
        //Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
        return (!isTokenExpired(jwt));
    }


    public String getUsernameFromJWT(String token) throws SignatureException, ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
