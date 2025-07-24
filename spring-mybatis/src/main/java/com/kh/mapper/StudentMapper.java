package com.kh.mapper;

import com.kh.dto.StudentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<StudentDTO> selectAllStudent();
    int deleteStudent(String sno);
    StudentDTO selectStudentBySno(String sno);
}
