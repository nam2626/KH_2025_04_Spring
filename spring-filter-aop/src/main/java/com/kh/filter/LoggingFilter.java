package com.kh.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@Component
public class LoggingFilter implements Filter {

  /**
   *  필터가 생성될 때(애플리케이션 시작시) 딱 한번만 실행 됩니다.
   */
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.info("=== LogginFilter 생성 : {} === ",filterConfig.getFilterName());
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) servletRequest;
    log.info("Request received : {}",request.getRequestURI());

    //필터 작업 수행 후 서블릿으로 요청을 전달, 이 코드가 없으면 요청이 중단됨
    filterChain.doFilter(servletRequest,servletResponse);
  }

  @Override
  public void destroy() {
    log.info("=== LogginFilter destroy ===");
  }
}
