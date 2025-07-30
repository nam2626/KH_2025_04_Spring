package com.member.mapper;

import com.member.dto.BoardMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMemberMapper {
  List<BoardMemberDTO> selectAllMember();
  int insertMember(Map<String, Object> requestBody);
  BoardMemberDTO selectMember(String id);
  int deleteMember(String id);
  int updateMember(Map<String, Object> body);
  List<BoardMemberDTO> searchMember(Map<String, Object> map);
}
