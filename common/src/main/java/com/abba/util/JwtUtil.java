package com.abba.util;

import com.abba.entity.Subject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Date;

/**
 * @author dengbojing
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class JwtUtil {

    private String key;

    private String clientId;

    private Long timeout;

    private ObjectMapper objectMapper;


    /**
     * 生成 JWT Token
     *
     * @param subject 数据
     * @return token
     */
    public String general(Subject subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = key();
        String message = "";
        try {
            message = objectMapper.writeValueAsString(subject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JwtBuilder builder = Jwts.builder()
                .setId(clientId)
                .setIssuedAt(now)
                .setSubject(message)
                .signWith(key, signatureAlgorithm);
        Date exp = new Date(nowMillis + timeout * 1000);
        builder.setExpiration(exp);
        return builder.compact();
    }

    /**
     * 解析Token
     *
     * @param jwt token
     * @return 解析结果
     */
    public Claims parse(String jwt) {
        return Jwts.parser().setSigningKey(key()).parseClaimsJws(jwt).getBody();
    }

    /**
     * 解析Token为subject
     *
     * @param jwt token
     * @return 解析结果
     */
    public Subject subject(String jwt) throws IOException, ExpiredJwtException {
        String subject = Jwts.parser().setSigningKey(key()).parseClaimsJws(jwt).getBody().getSubject();
        return objectMapper.readValue(subject, Subject.class);
    }

    /**
     * 获取私钥
     *
     * @return 私钥
     */
    private SecretKey key() {
        return Keys.hmacShaKeyFor(Base64Utils.encode(key.getBytes()));
    }
}
