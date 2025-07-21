package com.kh.spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    /*
        JSP/Servlet 기반에서 Servlet과 Controller 역할을 함
        Spring MVC에서는 메서드로 해당 역할을 함.
     */
    @GetMapping("/")
    public String main() {
        System.out.println("index 페이지 요청됨");
        return "index";
    }
}
