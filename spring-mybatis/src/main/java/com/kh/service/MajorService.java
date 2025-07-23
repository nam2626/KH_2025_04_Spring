package com.kh.service;

import com.kh.dto.MajorDTO;
import com.kh.mapper.MajorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService {
    @Autowired
    private MajorMapper majorMapper;

    public List<MajorDTO> selectAllMajor() {
        return majorMapper.selectAllMajor();
    }
}
