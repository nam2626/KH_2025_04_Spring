package com.kh.controller;

import com.kh.dto.StudentDTO;
import com.kh.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/update/view")
    public ModelAndView updateStudentView(@RequestParam("sno") String sno, ModelAndView view) {
        StudentDTO dto = studentService.selectStudentBySno(sno);
        view.addObject("student", dto);
        view.setViewName("student_update_view");
        return view;
    }

    @PostMapping("/update")
    public String updateStudent(StudentDTO student){
        int result = studentService.updateStudent(student);
        System.out.println("수정 결과 : " + result);
        return "redirect:/";
    }

    @PostMapping("/insert")
    public String insertStudent(StudentDTO student){
        int result = studentService.insertStudent(student);
        System.out.println("추가 결과 : " + result);
        return "redirect:/";
    }
}
