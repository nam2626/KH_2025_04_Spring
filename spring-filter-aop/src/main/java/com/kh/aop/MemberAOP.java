package com.kh.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

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
  //JoinPoint 이후에 실행
  //모든 리턴 타입, com.kh.service 패키지 내에 있는 모든 클래스
  //모든 메서드, 모든 매개변수 형태 지원
  @After("execution(* com.kh.service.*.*(..))")
  public void afterTest(JoinPoint joinPoint){
    System.out.println("afterTest : " + joinPoint.getSignature().getDeclaringType().getName() + " / " + joinPoint.getSignature().getName());

    System.out.println("afterTest - args : " + Arrays.toString(joinPoint.getArgs()));
  }

  // 실행 후 리턴 값을 받을 때, 데이터가 없으면 null 값 받아옴
  @AfterReturning(pointcut = "execution(* com.kh.service.*.*(..))", returning = "returnObj")
  public void afterReturingTest(JoinPoint joinPoint, Object returnObj){
    System.out.println("afterReturningTest : " + joinPoint.getSignature().getDeclaringType().getName() + " / " + joinPoint.getSignature().getName());

    System.out.println("afterReturningTest : " + returnObj);
  }

  // 메서드에서 Exception을 throws 하는 경우 실행
  @AfterThrowing(pointcut = "execution(* com.kh.service.*.*(..))", throwing = "exception")
  public void afterThrowingTest(JoinPoint joinPoint, Exception exception){
    System.out.println("afterThrowingTest : " + joinPoint.getSignature().getDeclaringType().getName() + " / " + joinPoint.getSignature().getName());

    System.out.println("afterThrowingTest : " + exception.getMessage());
  }

  @Around("execution(* com.kh.mapper.*.*(..))")
  public Object aroundTest(ProceedingJoinPoint joinPoint) throws Throwable{
    System.out.println("Around Test Start");
    //메서드 실행 시간 체크해서 출력
    StopWatch stopWatch = new StopWatch();

    // 메서드 실행하는 부분
    stopWatch.start();
    Object obj = joinPoint.proceed();
    stopWatch.stop();
    System.out.println(stopWatch.prettyPrint());

    System.out.println("Around return : " + obj);
    System.out.println("Around Test End");

    return obj;
  }

}






