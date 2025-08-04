package com.kh.config;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CustomPasswordEncoder implements PasswordEncoder{
  @Override
  public String encode(CharSequence rawPassword) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(rawPassword.toString().getBytes());
      return Base64.getEncoder().encodeToString(hash);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean mathces(CharSequence rawPassword, String encodePassword) {
    return encode(rawPassword).equals(encodePassword);
  }
}
