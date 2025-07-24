package com.kh.controller;

import com.kh.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/delete/{sno}")
    public String deleteStudent(@PathVariable String sno){
        System.out.println("학생 삭제 요청: " + sno);
        int result = studentService.deleteStudent(sno);
        if (result > 0) {
            System.out.println("학생 삭제 성공: " + sno);
        } else {
            System.out.println("학생 삭제 실패: " + sno);
        }
        return "redirect:/";
    }
}
