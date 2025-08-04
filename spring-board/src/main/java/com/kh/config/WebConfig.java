package com.kh.config;

import com.kh.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  //JWT 인증필터 등록
  @Bean
  public FilterRegistrationBean<JwtAuthenticationFilter> jwtFilterRegistration(){
    FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(jwtAuthenticationFilter);
    registrationBean.addUrlPatterns("/api/*");
    registrationBean.setOrder(1);
    return registrationBean;
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")//모든 경로에 CORS 허용
            .allowedOrigins("http://localhost:3000")//React 서버 주소
            .allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
            .allowedHeaders("*").allowCredentials(true).maxAge(3600);
  }
}
