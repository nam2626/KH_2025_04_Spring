package com.member.service;

import com.member.dto.BoardMemberDTO;
import com.member.mapper.BoardMemberMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardMemberService {
  private BoardMemberMapper mapper;

  public BoardMemberService(BoardMemberMapper mapper) {
    this.mapper = mapper;
  }

  public List<BoardMemberDTO> selectAllMember() {
    return mapper.selectAllMember();
  }
}
