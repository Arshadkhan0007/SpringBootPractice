package com.example.SpringSecurityPractice1.SecurityConfiguration;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtUtil {

    private final Key secretKey;
    private final long accessTokenExpirationTimeInMinutes;
    private final long refreshTokenExpirationTimeInDays;
    private final String ACCESS_TOKEN_TYPE;
    private final String REFRESH_TOKEN_TYPE;
    private final String CLAIM_ROLES = "roles";
    private final String CLAIM_TOKEN_TYPE = "tokenType";

    public JwtUtil(@Value("${security.secret-key}") String secretKey,
                   @Value("${security.expiration-time.access-minutes}") long accessTokenExpirationTimeInMinutes,
                   @Value("${security.expiration-time.refresh-days}") int refreshTokenExpirationTimeInDays,
                   @Value("${security.token-type.access-token}") String ACCESS_TOKEN_TYPE,
                   @Value("${security.token-type.refresh-token}") String REFRESH_TOKEN_TYPE){


        byte[] keyBytes = Decoders.BASE64.decode(secretKey.trim());
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpirationTimeInMinutes = accessTokenExpirationTimeInMinutes;
        this.refreshTokenExpirationTimeInDays = refreshTokenExpirationTimeInDays;
        this.ACCESS_TOKEN_TYPE = ACCESS_TOKEN_TYPE;
        this.REFRESH_TOKEN_TYPE = REFRESH_TOKEN_TYPE;
    }

    public String generateAccessToken(UserDetails userDetails){

            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            Map<String, Object> customClaims = new HashMap<>();
            customClaims.put(CLAIM_TOKEN_TYPE, ACCESS_TOKEN_TYPE);
            customClaims.put(CLAIM_ROLES, authorities
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet()));

            return Jwts
                    .builder()
                    .setClaims(customClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(accessTokenExpirationTimeInMinutes))))
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();

    }

    public String generateRefreshToken(UserDetails userDetails){

            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            Map<String, Object> customClaims = new HashMap<>();
            customClaims.put(CLAIM_TOKEN_TYPE, REFRESH_TOKEN_TYPE);
            customClaims.put(CLAIM_ROLES, authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet()));
            return Jwts
                    .builder()
                    .setClaims(customClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(Date.from(Instant.now().plus(Duration.ofDays(refreshTokenExpirationTimeInDays))))
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();

    }

    public String getTokenType(String token){
        return extractClaim(token, claims -> claims.get(CLAIM_TOKEN_TYPE, String.class));
    }

    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);

    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {

        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);

    }

    private Claims extractAllClaims(String token) {

            return Jwts
                    .parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

    }

    public boolean validateToken(String token, UserDetails userDetails) {

        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isExpired(token));

    }

    private boolean isExpired(String token) {

        return extractExpireDate(token).before(new Date());

    }

    private Date extractExpireDate(String token) {

        return extractClaim(token, Claims::getExpiration);

    }

}
