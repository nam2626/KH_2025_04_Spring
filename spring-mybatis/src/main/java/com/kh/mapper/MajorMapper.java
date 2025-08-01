package com.kh.mapper;

import com.kh.dto.MajorDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MajorMapper {
  List<MajorDTO> selectAllMajor();
  int deleteMajor(String mno);
  int updateMajor(Map<String, Object> body);
  int insertMajor(Map<String, Object> body);
  List<MajorDTO> searchMajor(String search);
}
