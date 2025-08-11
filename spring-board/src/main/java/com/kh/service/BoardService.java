package com.kh.service;

import com.kh.dto.BoardCommentDTO;
import com.kh.dto.BoardDTO;
import com.kh.dto.BoardFileDTO;
import com.kh.mapper.BoardMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

  public int insertBoardLike(int bno, String id) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("bno", bno);
    map.put("id", id);
    return mapper.insertBoardLike(map);
  }

  public Map<String, Object> selectBoardLikeHateCount(int bno) {
    return mapper.selectBoardLikeHateCount(bno);
  }

  public int deleteBoardLike(int bno, String id) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("bno", bno);
    map.put("id", id);
    return mapper.deleteBoardLike(map);
  }

  public int insertBoardHate(int bno, String id) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("bno", bno);
    map.put("id", id);
    return mapper.insertBoardHate(map);
  }
  public int deleteBoardHate(int bno, String id) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("bno", bno);
    map.put("id", id);
    return mapper.deleteBoardHate(map);
  }

  public int insertBoardCommentLike(int cno, String id) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("cno", cno);
    map.put("id", id);
    return mapper.insertBoardCommentLike(map);
  }
  public int deleteBoardCommentLike(int cno, String id) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("cno", cno);
    map.put("id", id);
    return mapper.deleteBoardCommentLike(map);
  }
  public Map<String, Object> selectBoardCommentLikeHateCount(int cno) {
    return mapper.selectBoardCommentLikeHateCount(cno);
  }

  public int insertBoardCommentHate(int cno, String id) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("cno", cno);
    map.put("id", id);
    return mapper.insertBoardCommentHate(map);
  }
  public int deleteBoardCommentHate(int cno, String id) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("cno", cno);
    map.put("id", id);
    return mapper.deleteBoardCommentHate(map);
  }

  public int deleteBoard(int bno, String id) {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("bno", bno);
    map.put("id", id);
    return mapper.deleteBoard(map);
  }

  public BoardCommentDTO selectBoardComment(int cno) {
    return mapper.selectBoardComment(cno);
  }

  public int deleteBoardComment(int cno, String authenticatedUserid) {
    Map<String, Object> map = Map.of("cno", cno,"id",authenticatedUserid);
    return mapper.deleteBoardComment(map);
  }


  public int updateBoardComment(BoardCommentDTO comment) {
    return mapper.updateBoardComment(comment);
  }

  public int selectBoardNo() {
    return mapper.selectBoardNo();
  }

  public int insertBoard(BoardDTO board, List<BoardFileDTO> fileList) {
    int count = mapper.insertBoard(board);
    fileList.forEach(item -> mapper.insertBoardFile(item));
    return count;
  }

  public BoardFileDTO selectFile(int fno) {
    return mapper.selectFile(fno);
  }

  public int insertBoardComment(BoardCommentDTO commentDTO) {
    return mapper.insertBoardComment(commentDTO);
  }
}
