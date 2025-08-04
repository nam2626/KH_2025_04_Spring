package com.kh.mapper;

import com.kh.dto.BoardMemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
  BoardMemberDTO findByUserid(String userid);
  void registerUser(BoardMemberDTO newUser);
}
