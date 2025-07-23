package com.kh.service;

import com.kh.dto.StudentDTO;
import com.kh.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private StudentMapper studentMapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    public List<StudentDTO> selectAllStudent() {
        return studentMapper.selectAllStudent();
    }
}
