package com.member.service;

import com.member.dto.BoardMemberDTO;
import com.member.mapper.BoardMemberMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardMemberService {
  private BoardMemberMapper mapper;

  public BoardMemberService(BoardMemberMapper mapper) {
    this.mapper = mapper;
  }

  public List<BoardMemberDTO> selectAllMember() {
    return mapper.selectAllMember();
  }

  public int insertMember(Map<String, Object> requestBody) {
    return mapper.insertMember(requestBody);
  }

  public BoardMemberDTO selectMember(String id) {
    return mapper.selectMember(id);
  }

  public int deleteMember(String id) {
    return mapper.deleteMember(id);
  }

  public int updateMember(Map<String, Object> body) {
    return mapper.updateMember(body);
  }

  public List<BoardMemberDTO> searchMember(String kind, String search) {
    Map<String, Object> map = new HashMap<>();
    map.put("kind",kind);
    map.put("search", search);
    return mapper.searchMember(map);
  }
}
