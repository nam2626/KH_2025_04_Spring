package com.kh.service;

import com.kh.dto.BoardDTO;
import com.kh.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class BoardService {
  private BoardMapper mapper;

  public BoardService(BoardMapper mapper) {
    this.mapper = mapper;
  }

  public List<BoardDTO> selectBoardList(int pageNo, int pageContentEa)  {
    Map<String, Object> map = Map.of("pageNo",pageNo,"pageContentEa",pageContentEa);
    return mapper.selectBoardList(map);
  }

  public int selectBoardTotalCount() {
    return mapper.selectBoardTotalCount();
  }
}
