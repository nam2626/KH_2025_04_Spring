package com.kh.controller;

import com.kh.config.PasswordEncoder;
import com.kh.dto.AuthRequest;
import com.kh.dto.BoardMemberDTO;
import com.kh.dto.JwtResponse;
import com.kh.service.UserService;
import com.kh.util.JwtTokenProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtTokenProvider jwtTokenProvider;

  @PostMapping("/signup")
  public ResponseEntity<String> signup(@Valid @RequestBody AuthRequest request){
    System.out.println(request);
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

  @PostMapping("/login")
  public ResponseEntity<JwtResponse> login(@Valid @RequestBody AuthRequest request, HttpServletResponse response){
    //1. 사용자 입력한 아이디 값에 해당하는 회원정보를 조회해서 가져옴
    BoardMemberDTO user = userService.findByUserid(request.getUserid());
    //2. 사용자가 입력한 암호를 암호화 한 후에 회원 정보의 암호와 비교
    if(user == null || !passwordEncoder.mathces(request.getPassword(), user.getPassword())){
      //인증 실패
      return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }
    //3. 토큰 생성(access, refresh)
    String accessToken = jwtTokenProvider.generateAccessToken(user);
    String refreshToken = jwtTokenProvider.geneateRefreshToken(user);

    Cookie refreshTokenCookie = new Cookie("refreshToken",refreshToken);
    refreshTokenCookie.setHttpOnly(true);
//    refreshTokenCookie.setSecure(true);//HTTPS 통신에서만 허용
    refreshTokenCookie.setSecure(false);
    refreshTokenCookie.setPath("/");//모든 경로에서 접근 가능
    refreshTokenCookie.setMaxAge((int)(jwtTokenProvider.getRefreshTokenExpirationMs() / 1000));

    response.addCookie(refreshTokenCookie);

    return ResponseEntity.ok(new JwtResponse(accessToken,refreshToken,"Bearer"));

  }

  @GetMapping("/user-data")
  public ResponseEntity<String> getUserData(HttpServletRequest request){
    //회원 아이디를 꺼냄, request으로부터 추출
    String id = (String) request.getAttribute("authenticatedUserid");

    //회원 정보 조회해서 사용자에게 전달
    BoardMemberDTO user = userService.findByid(Long.parseLong(id));
    System.out.println("아이디 값 : "+ id);
    System.out.println("사용자 정보 : " + user);

    if (user == null) {
      Map<String, String> errorResponse = Map.of("message","사용자를 찾으수 없습니다.","id",id);
        return new ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(user,HttpStatus.OK);
  }
}









