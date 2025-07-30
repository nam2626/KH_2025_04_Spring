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
    try {
      int result = service.insertMember(requestBody);
      if (result != 0)
        map.put("result", true);
      else
        map.put("result", false);
    }catch (Exception e){
      System.out.println(e.getMessage());
      map.put("result", false);
    }
    return map;
  }

  @GetMapping("/{id}")
  public Map<String, Object> checkId(@PathVariable String id){
    Map<String, Object> map = new HashMap<>();

    BoardMemberDTO member = service.selectMember(id);

    map.put("result", member == null ? false : true);

    return map;
  }
  @GetMapping("/update/{id}")
  public Map<String, Object> updateMemberInfo(@PathVariable String id){
    Map<String, Object> map = new HashMap<>();

    BoardMemberDTO member = service.selectMember(id);
    if(member != null)
      map.put("member", member);
    map.put("result", member == null ? false : true);

    return map;
  }
  @DeleteMapping("/{id}")
  public Map<String, Object> deleteMember(@PathVariable String id){
    Map<String, Object> map = new HashMap<>();

    int result = service.deleteMember(id);

    if(result == 0)
      map.put("msg","데이터 삭제 실패");
    else
      map.put("msg","데이터 삭제 성공");

    map.put("result", result);

    return map;
  }
  //회원정보 Update 메서드 추가
  @PatchMapping("/update")
  public Map<String, Object> updateMember(@RequestBody Map<String, Object> body){
    Map<String, Object> map = new HashMap<>();
    System.out.println(body);

    int result = service.updateMember(body);

    map.put("result",result == 0 ? false : true);

    return map;
  }
  @GetMapping("/search")
  public Map<String, Object> searchMember(String kind, String search){
    System.out.println(kind +" " + search);
    List<BoardMemberDTO> list = service.searchMember(kind, search);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("memberList",list);
    map.put("count",list.size());
    return map;
  }
}




