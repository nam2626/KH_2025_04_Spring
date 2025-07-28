package com.member.service;

import com.member.mapper.BoardMemberMapper;
import org.springframework.stereotype.Service;

@Service
public class BoardMemberService {
  private BoardMemberMapper mapper;

  public BoardMemberService(BoardMemberMapper mapper) {
    this.mapper = mapper;
  }
}
