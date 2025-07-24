package com.kh.controller;

import com.kh.dto.MajorDTO;
import com.kh.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/major")
@RestController
public class MajorController {
  @Autowired
  private MajorService majorService;
  //삭제
  @DeleteMapping("/{mno}")
  public Map<String, Object> deleteMajor(@PathVariable String mno){
    Map<String, Object> map = new HashMap<>();
    System.out.println("삭제할 학과번호 : " + mno);
    //실제 삭제 작업 수행
    int count = majorService.deleteMajor(mno);
    if(count == 0)
      map.put("msg","학과 정보 삭제 실패");
    else
      map.put("msg","학과 정보 삭제 성공");
    map.put("result",count);
    //전체 학과정보 조회해서 map에 넣어서 전달
    List<MajorDTO> majorList = majorService.selectAllMajor();
    map.put("majorList", majorList);
    return map;
  }

  //수정
  @PatchMapping
  public Map<String, Object> updateMajor(@RequestBody Map<String, Object> body){
    Map<String, Object> map = new HashMap<>();
    System.out.println(body);
    //학과 정보 수정

    //전체 학과정보 조회해서 map에 넣어서 전달
    List<MajorDTO> majorList = majorService.selectAllMajor();
    map.put("majorList", majorList);
    return map;
  }

  //추가

  //조회

}
