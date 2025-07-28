package com.member.controller;

import com.member.dto.BoardMemberDTO;
import com.member.service.BoardMemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MainController {
  private BoardMemberService service;

  public MainController(BoardMemberService service) {
    this.service = service;
  }

  @GetMapping("/list")
  public Map<String, Object> selectAllMember(){
    List<BoardMemberDTO> list = service.selectAllMember();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("memberList",list);
    map.put("count",list.size());
    return map;
  }
}
