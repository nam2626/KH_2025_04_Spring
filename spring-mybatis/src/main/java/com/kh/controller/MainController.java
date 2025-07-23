package com.kh.controller;

import com.kh.dto.StudentDTO;
import com.kh.service.MajorService;
import com.kh.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private StudentService studentService;
    private MajorService majorService;

    public MainController(StudentService studentService, MajorService majorService) {
        this.studentService = studentService;
        this.majorService = majorService;
    }

    @GetMapping("/")
    public ModelAndView main(ModelAndView view) {
        //학생 정보 목록 받음
        List<StudentDTO> studentList = studentService.selectAllStudent();
        System.out.println(studentList);
        view.addObject("studentList", studentList);
        view.setViewName("main");
        return view;
    }
}
