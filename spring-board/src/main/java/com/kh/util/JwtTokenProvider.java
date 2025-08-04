package com.kh.util;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtTokenProvider {
  @Value("${jwt.secret}")
  private String secretKey;
  @Value("${jwt.access-token-expiration-ms}")
  private long accessTokenExpirationMs;
  @Value("${jwt.refresh-token-expiration-ms}")
  private long refreshTokenExpirationMs;

  private Key key;
  @PostConstruct
  public void init(){
    this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
  }
}
