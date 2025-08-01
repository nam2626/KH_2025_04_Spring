package com.kh.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class MemberAOP {
  //포인트컷에서는 제네릭은 적용되지 않음
  // JoinPoint 이전에 실행
  // 리턴타입 List, com.jh.service에있는 모든 클래스의 모든 메서드의 매개변수가 String 인것만 대상
  @Before("execution(java.util.List com.kh.service.*.*(String))")
  public void loginTest(JoinPoint joinPoint){
    System.out.println(joinPoint.getSignature().getDeclaringType().getName() + " / " + joinPoint.getSignature().getName());
    System.out.println(Arrays.toString(joinPoint.getArgs()));
  }
  // 모든 리턴타입, com.kh 패키지 하위 패키지까지 포함, 모든 클래스, 모든 메서드, 매개변수는 모든 형태를 지원
  @Before("execution(* com.kh..*.*(..))")
  public void beforeTest(JoinPoint joinPoint){
    System.out.println("beforeTest : " + joinPoint.getSignature().getDeclaringType().getName() + " / " + joinPoint.getSignature().getName());
    System.out.println("beforeTest - args : " + Arrays.toString(joinPoint.getArgs()));
  }
}







