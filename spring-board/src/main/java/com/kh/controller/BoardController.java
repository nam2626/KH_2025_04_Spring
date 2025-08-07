package com.kh.controller;

import com.kh.dto.BoardCommentDTO;
import com.kh.dto.BoardDTO;
import com.kh.dto.BoardFileDTO;
import com.kh.service.BoardService;
import com.kh.util.JwtTokenProvider;
import com.kh.vo.PaggingVO;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/board")
public class BoardController {

  private BoardService boardService;
  private JwtTokenProvider tokenProvider;

  public BoardController(BoardService boardService, JwtTokenProvider tokenProvider) {
    this.boardService = boardService;
    this.tokenProvider = tokenProvider;
  }

  //게시글 목록 조회
  //페이지 번호(기본값 : 1), 한번에 가져올 게시글 개수(기본값 : 30)
  @GetMapping("/list")
  public Map<String, Object> main(@RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "30") int pageContentEa){
    //게시글 리스트 받음
//		List<BoardDTO> list = BoardService.getInstance().selectAllBoard();
    List<BoardDTO> list = boardService.selectBoardList(pageNo, pageContentEa);
    //PaggingVO 생성
    //전체 게시글 개수를 받아옴
    int count = boardService.selectBoardTotalCount();
    PaggingVO pagging = new PaggingVO(count, pageNo, pageContentEa);
    Map<String, Object> map = new HashMap<>();
    map.put("boardList",list);
    map.put("pagging",pagging);

    return map;
  }

  @GetMapping("/detail/{bno}")
  public Map<String, Object> boardDetail(@PathVariable int bno) {
    Map<String, Object> map = new HashMap<>();
    //글번호에 해당하는 게시글 조회
    BoardDTO board = boardService.selectBoard(bno);
    //글번호에 해당하는 댓글 리스트 생성
    List<BoardCommentDTO> commentList = boardService.selectCommentList(bno, 1);
    //글번호에 해당하는 첨부파일 리스트 생성
    List<BoardFileDTO> fileList = boardService.selectFileList(bno);

    map.put("board", board);
    map.put("commentList", commentList);
    map.put("fileList", fileList);

    return map;
  }

  @GetMapping("/like/{bno}")
  public Map<String, Object> boardLike(@PathVariable int bno, @RequestAttribute String authenticatedUserid){
    Map<String, Object> map = new HashMap<>();
    System.out.println(bno + " " + authenticatedUserid);
    try {
      //좋아요 처리
      boardService.insertBoardLike(bno,authenticatedUserid);
      map.put("code", 1);
      map.put("msg","해당 게시글에 좋아요 하셨습니다.");
    } catch (Exception e) {
      //이미 좋아요 했으면 취소처리
      boardService.deleteBoardLike(bno,authenticatedUserid);
      map.put("code", 1);
      map.put("msg","해당 게시글에 좋아요 취소하셨습니다.");
    }finally {
      map.put("count",boardService.selectBoardLikeHateCount(bno));
    }

    return map;
  } 
  @GetMapping("/comment/hate/{cno}")
  public Map<String, Object> boardCommentHate(@PathVariable int cno, @RequestAttribute String authenticatedUserid){
    Map<String, Object> map = new HashMap<>();
    System.out.println(cno + " " + authenticatedUserid);
    try {
      //좋아요 처리
      boardService.insertBoardCommentHate(cno,authenticatedUserid);
      map.put("code", 1);
      map.put("msg","해당 댓글에 싫어요 하셨습니다.");
    } catch (Exception e) {
      //이미 좋아요 했으면 취소처리
      boardService.deleteBoardCommentHate(cno,authenticatedUserid);
      map.put("code", 1);
      map.put("msg","해당 댓글에 싫어요 취소하셨습니다.");
    }finally {
      map.put("count",boardService.selectBoardCommentLikeHateCount(cno));
    }

    return map;
  }

  @GetMapping("/comment/like/{cno}")
  public Map<String, Object> boardCommentLike(@PathVariable int cno, @RequestAttribute String authenticatedUserid){
    Map<String, Object> map = new HashMap<>();
    System.out.println(cno + " " + authenticatedUserid);
    try {
      //좋아요 처리
      boardService.insertBoardCommentLike(cno,authenticatedUserid);
      map.put("code", 1);
      map.put("msg","해당 댓글에 좋아요 하셨습니다.");
    } catch (Exception e) {
      //이미 좋아요 했으면 취소처리
      boardService.deleteBoardCommentLike(cno,authenticatedUserid);
      map.put("code", 1);
      map.put("msg","해당 댓글에 좋아요 취소하셨습니다.");
    }finally {
      map.put("count",boardService.selectBoardCommentLikeHateCount(cno));
    }

    return map;
  }
  @GetMapping("/hate/{bno}")
  public Map<String, Object> boardHate(@PathVariable int bno, @RequestAttribute String authenticatedUserid){
    Map<String, Object> map = new HashMap<>();
    System.out.println(bno + " " + authenticatedUserid);
    try {
      //좋아요 처리
      boardService.insertBoardHate(bno,authenticatedUserid);
      map.put("code", 1);
      map.put("msg","해당 게시글에 싫어요 하셨습니다.");
    } catch (Exception e) {
      //이미 좋아요 했으면 취소처리
      boardService.deleteBoardHate(bno,authenticatedUserid);
      map.put("code", 1);
      map.put("msg","해당 게시글에 싫어요 취소하셨습니다.");
    }finally {
      map.put("count",boardService.selectBoardLikeHateCount(bno));
    }

    return map;
  }

}






