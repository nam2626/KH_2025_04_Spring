package com.kh.mapper;

import com.kh.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
  List<BoardDTO> selectBoardList(Map<String, Object> map);
  int selectBoardTotalCount();
}
