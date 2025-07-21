package com.kh.spring_di.context;

import com.kh.spring_di.vo.Greeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

    @Bean
    public Greeting greeter(){
        System.out.println("greeter 메서드 호출");
        return new Greeting(1000,"홍길동");
    }
}
