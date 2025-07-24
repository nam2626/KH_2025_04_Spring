package com.kh.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/major")
@RestController
public class MajorController {

  //삭제
  @DeleteMapping("/major/{mno}")
  public Map<String, Object> deleteMajor(@PathVariable String mno){
    System.out.println("삭제할 학과번호 : " + mno);
    Map<String, Object> map = new HashMap<>();

    return map;
  }

  //추가

  //수정

  //조회

}
