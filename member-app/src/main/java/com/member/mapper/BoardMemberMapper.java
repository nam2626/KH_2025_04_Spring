package com.member.mapper;

import com.member.dto.BoardMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMemberMapper {
  List<BoardMemberDTO> selectAllMember();
}
