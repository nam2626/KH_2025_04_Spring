package com.kh.service;

import com.kh.dto.MajorDTO;
import com.kh.mapper.MajorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MajorService {
    @Autowired
    private MajorMapper majorMapper;

    public List<MajorDTO> selectAllMajor() {
        return majorMapper.selectAllMajor();
    }

  public int deleteMajor(String mno) {
      return majorMapper.deleteMajor(mno);
  }

  public int updateMajor(Map<String, Object> body) {
    return majorMapper.updateMajor(body);
  }
}
