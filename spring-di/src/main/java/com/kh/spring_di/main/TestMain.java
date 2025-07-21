package com.kh.spring_di.main;

import com.kh.spring_di.context.AppContext;
import com.kh.spring_di.vo.Greeting;
import com.kh.spring_di.vo.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppContext.class);

        // Greeting 객체를 가져오기
        Greeting g1 = ctx.getBean("greeter", Greeting.class);
        System.out.println(g1);
        Greeting g2 = ctx.getBean("greeter", Greeting.class);
        System.out.println(g2);
        Greeting g3 = ctx.getBean("greeter", Greeting.class);
        System.out.println(g3);
        Greeting g4 = ctx.getBean("greeter", Greeting.class);
        System.out.println(g4);
        System.out.println("g1 == g2 ? " + (g1 == g2));

        Person p1 = ctx.getBean("person", Person.class);
        Person p2 = ctx.getBean("person", Person.class);
        Person p3 = ctx.getBean("person", Person.class);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println("p1 == p2 ? " + (p1 == p2));
    }
}
