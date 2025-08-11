package com.kh.mapper;

import com.kh.dto.BoardCommentDTO;
import com.kh.dto.BoardDTO;
import com.kh.dto.BoardFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
  List<BoardDTO> selectBoardList(Map<String, Object> map);
  int selectBoardTotalCount();
  BoardDTO selectBoard(int bno);
  List<BoardCommentDTO> selectCommentList(Map<String, Object> map);
  List<BoardFileDTO> selectFileList(int bno);
  int insertBoardLike(Map<String, Object> map);
  Map<String, Object> selectBoardLikeHateCount(int bno);
  int deleteBoardLike(Map<String, Object> map);
  int insertBoardHate(Map<String, Object> map);
  int deleteBoardHate(Map<String, Object> map);
  int insertBoardCommentLike(Map<String, Object> map);
  int deleteBoardCommentLike(Map<String, Object> map);
  Map<String, Object> selectBoardCommentLikeHateCount(int cno);
  int insertBoardCommentHate(Map<String, Object> map);
  int deleteBoardCommentHate(Map<String, Object> map);
  int deleteBoard(Map<String, Object> map);
  BoardCommentDTO selectBoardComment(int cno);
  int deleteBoardComment(Map<String, Object> map);
  int updateBoardComment(BoardCommentDTO comment);
  int selectBoardNo();
  int insertBoard(BoardDTO board);
  void insertBoardFile(BoardFileDTO item);
  BoardFileDTO selectFile(int fno);
}
