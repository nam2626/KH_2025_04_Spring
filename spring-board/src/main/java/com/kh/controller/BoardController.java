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
    //좋아요 처리

    //이미 좋아요 했으면 취소처리

    return map;
  }
}






