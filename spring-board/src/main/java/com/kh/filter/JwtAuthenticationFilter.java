package com.kh.filter;

import com.kh.util.JwtTokenProvider;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter {
  private final JwtTokenProvider jwtTokenProvider;

  //1. 인증이 필요없는 경로 목록을 리스트로 관리
  private static final List<String> PUBLIC_PATHS = Arrays.asList(
          "/api/auth/login",
          "/api/auth/signup",
          "/api/auth/refresh"
  );

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("Filter init");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    //1. OPTIONS 메서드(CORS Pre-flight)는 즉시 통과
    if(httpRequest.getMethod().equals("OPTIONS")){
      chain.doFilter(request,response);
      return;
    }

    //2. 요청 경로를 뽑음
    String path = httpRequest.getServletPath();

    //3. 리스트에 포함된 경로인지 확인.
    boolean isPublicPath = PUBLIC_PATHS.stream().anyMatch(path::startsWith);
    //4. 리스트에 포함된 경로면 인증 없이 바로 통과
    if(isPublicPath){
      chain.doFilter(request,response);
      return;
    }
    //5. 인증이 있어야 통과
    //http header에서 토큰 추출
    String token = resolveToken(httpRequest);
    //토큰이 유효한지 체크, 사용자 정보를 추출해서 request에 저장
    if(token != null && jwtTokenProvider.validateToken(token)){
      //토큰이 유효할 때
      String id = jwtTokenProvider.getUseridFromToken(token);
      String roles = jwtTokenProvider.getRolesFromToken(token);

      httpRequest.setAttribute("authenticatedUserid",id);
      httpRequest.setAttribute("authenticatedRoles",roles);

      chain.doFilter(httpRequest,httpResponse);
    }else{
      //토큰이 유효하지 않을 때
      httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
      httpResponse.setContentType("application/json");
      httpResponse.setCharacterEncoding("UTF-8");
      httpResponse.getWriter().write("{\"message\" : \"권한 없음: 유효하지 않거나 토큰이 없습니다 \"}");
    }

  }
  //토큰 추출하는 메서드
  private String resolveToken(HttpServletRequest request){
    String bearerToken = request.getHeader("Authorizarion");
    if(bearerToken != null && bearerToken.startsWith("Bearer "))
      return bearerToken.substring(7);
    return null;
  }


  @Override
  public void destroy() {
    System.out.println("Filter destory");
  }
}
