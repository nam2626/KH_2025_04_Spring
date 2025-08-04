package com.kh.controller;

import com.kh.config.PasswordEncoder;
import com.kh.service.UserService;
import com.kh.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;



}









