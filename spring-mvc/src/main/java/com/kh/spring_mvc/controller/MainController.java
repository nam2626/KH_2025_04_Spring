package com.kh.spring_mvc.controller;

import jakarta.servlet.http.HttpSession;
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

    /*@PostMapping("/member/login")
    public String login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        session.setAttribute("msg", "<b>로그인 성공</b>");
        System.out.println(username + " : " + password);
        return "main";
    }*/
    @PostMapping("/member/login")
    public String login(String username, String password, HttpSession session) {
        session.setAttribute("msg", "<b>로그인 성공</b>");
        System.out.println(username + " / " + password);
        return "main";
    }

    @GetMapping("/member/register/view")
    public String registerView() {
        System.out.println("회원가입 페이지로 이동");
        return "register"; // forward 방식으로 이동
    }

    // /member/register로 POST 요청을 받아서 MemberDTO 객체를 생성
    // MemberDTO 객체는 request 영역에 저장
    // register_result.html로 이동

}









