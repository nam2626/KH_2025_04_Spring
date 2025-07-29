package com.member.controller;

import com.member.dto.BoardMemberDTO;
import com.member.service.BoardMemberService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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

  @PostMapping("/register")
  public Map<String, Object> insertMember(@RequestBody Map<String, Object> requestBody){
    Map<String, Object> map = new HashMap<>();
    System.out.println(requestBody);
    int result = service.insertMember(requestBody);
    if(result != 0)
      map.put("result", true);
    else
      map.put("result", false);
    return map;
  }
}
