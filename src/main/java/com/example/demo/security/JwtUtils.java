package com.example.demo.security;

import com.example.demo.model.entity.UserEntity;
import com.example.demo.model.mapper.PermissionMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtils {
    private final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private static final String JwtSecret = "QLSC6nSI0R7vBmyBsDX0J4XENTlkW4mI96gY7Wj_mAmiJFQIr7klqVHhQJmiQTSb1tHEYz222MzvucimU9tpvA";

    public String generateAccessToken(UserEntity user) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getId(), user.getUsername()))
                .setExpiration(new Date(System.currentTimeMillis() + 12 * 60 * 60 * 1000)) // 12 hours
                .claim("permissions", PermissionMapper.INSTANCE.toStringList(user.getRoleEntity().getPermissions()))
                .signWith(SignatureAlgorithm.HS512, JwtSecret)
                .compact();
    }

    public Boolean isTokenValid(String token, UserEntity user) {
        logger.info("Token non-expired: {}", getAllClaims(token).getExpiration().after(new Date()));
        logger.info("Username valid: {}", getUsername(token).equals(user.getUsername()));
        return getAllClaims(token).getExpiration().after(new Date()) && getUsername(token).equals(user.getUsername());
    }

    public String getUsername(String token) {
        return getAllClaims(token).getSubject().split(",")[1];
    }

    private Claims getAllClaims(String token) {
        return Jwts.parser().setSigningKey(JwtSecret).parseClaimsJws(token).getBody();
    }

}
