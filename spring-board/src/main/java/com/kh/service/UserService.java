package com.kh.service;

import com.kh.dto.BoardMemberDTO;
import com.kh.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserMapper userMapper;

  public BoardMemberDTO findByUserid(String userid) {
    return userMapper.findByUserid(userid);
  }

  public void registerUser(BoardMemberDTO newUser) {
    userMapper.registerUser(newUser);
  }
}
