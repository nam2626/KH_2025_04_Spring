package com.kh.spring_mvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/member/login/view")
    public String loginView() {
        System.out.println("로그인 페이지 요청됨");
        return "login";// forward 방식으로 이동
    }

    @PostMapping("/member/login")
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username + " : " + password);
        return "main";
    }
}









