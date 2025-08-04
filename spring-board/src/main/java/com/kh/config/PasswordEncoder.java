package com.kh.config;

public interface PasswordEncoder {
  String encode(CharSequence rawPassword);
  boolean mathces(CharSequence rawPassword, String encodePassword);
}
