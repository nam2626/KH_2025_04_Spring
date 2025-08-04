package com.kh.controller;

import com.kh.config.PasswordEncoder;
import com.kh.dto.AuthRequest;
import com.kh.dto.BoardMemberDTO;
import com.kh.service.UserService;
import com.kh.util.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@Valid @RequestBody AuthRequest request){
    if(userService.findByUserid(request.getUserid()) != null){
      return ResponseEntity.status(HttpStatus.CONFLICT).body("사용자 아이디가 이미 존재합니다.");
    }

    BoardMemberDTO newUser = new BoardMemberDTO();
    newUser.setUserid(request.getUserid());
    newUser.setPassword(passwordEncoder.encode(request.getPassword()));
    newUser.setUsername(request.getUsername());
    newUser.setRole("ROLE_USER");

    userService.registerUser(newUser);
    return ResponseEntity.status(HttpStatus.CREATED).body("사용자가 정상적으로 등록되었습니다.");
  }

}









