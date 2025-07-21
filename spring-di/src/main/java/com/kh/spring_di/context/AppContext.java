package com.kh.spring_di.context;

import com.kh.spring_di.vo.Greeting;
import com.kh.spring_di.vo.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

    // @Bean 어노테이션이 붙은 메서드는 Spring 컨테이너에 의해 관리되는 외부 객체를 생성해서 관리합니다.
    @Bean
    public Greeting greeter(){
        System.out.println("greeter 메서드 호출");
        return new Greeting(1000,"홍길동");
    }

    @Bean
    public Person person(){
        System.out.println("person 메서드 호출");
        return new Person("홍길동", 20);
    }
}
