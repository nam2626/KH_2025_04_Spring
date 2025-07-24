package com.kh.controller;

import com.kh.dto.MajorDTO;
import com.kh.dto.StudentDTO;
import com.kh.service.MajorService;
import com.kh.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<MajorDTO> majorList = majorService.selectAllMajor();
        //학과 리스트를 맵으로 변환
//        Map<String, String> majorMap = new HashMap<>();
//        for (MajorDTO major : majorList) {
//            majorMap.put(major.getMno(), major.getMname());
//        }
        Map<String, String> majorMap = majorList.stream()
                .collect(HashMap::new, (map, major) -> map.put(major.getMno(), major.getMname()), HashMap::putAll);
        view.addObject("studentList", studentList);
        view.addObject("majorList", majorList);
        view.addObject("majorMap", majorMap);
        view.setViewName("main");
        return view;
    }
}
