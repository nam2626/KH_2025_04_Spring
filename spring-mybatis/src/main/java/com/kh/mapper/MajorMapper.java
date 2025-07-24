package com.kh.mapper;

import com.kh.dto.MajorDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MajorMapper {
  List<MajorDTO> selectAllMajor();
  int deleteMajor(String mno);
}
