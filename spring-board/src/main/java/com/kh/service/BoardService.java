package com.kh.service;

import com.kh.dto.BoardCommentDTO;
import com.kh.dto.BoardDTO;
import com.kh.dto.BoardFileDTO;
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

  public BoardDTO selectBoard(int bno) {
    return mapper.selectBoard(bno);
  }

  public List<BoardCommentDTO> selectCommentList(int bno, int i) {
    Map<String,Object> map = Map.of("bno",bno, "page",i);
    return mapper.selectCommentList(map);
  }

  public List<BoardFileDTO> selectFileList(int bno) {
    return mapper.selectFileList(bno);
  }
}
