package com.kh.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.dto.BoardCommentDTO;
import com.kh.dto.BoardDTO;
import com.kh.dto.BoardFileDTO;
import com.kh.service.BoardService;
import com.kh.util.JwtTokenProvider;
import com.kh.vo.PaggingVO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

  @DeleteMapping("/{bno}")
  public Map<String, Object> boardDelete(@PathVariable int bno, @RequestAttribute String authenticatedUserid){
    BoardDTO board = boardService.selectBoard(bno);
    Map<String, Object> map = new HashMap<>();
    if(board.getId() == Long.parseLong(authenticatedUserid)){
      //1. 첨부파일 삭제
      List<BoardFileDTO> fileList = boardService.selectFileList(bno);
      fileList.forEach(file -> {
        File f = new File(file.getFpath());
        f.delete();
      });

      //2. 게시글 데이터 삭제
      boardService.deleteBoard(bno,authenticatedUserid);
      map.put("code",1);
      map.put("msg","해당 게시글을 삭제 완료하였습니다.");
    }else{
      map.put("code",2);
      map.put("msg","본인이 작성한 게시글만 삭제할 수 있습니다.");
    }
    return map;
  }

  @DeleteMapping("/comment/{cno}")
  public Map<String, Object> boardCommentDelete(@PathVariable int cno, @RequestAttribute String authenticatedUserid){
    BoardCommentDTO comment = boardService.selectBoardComment(cno);
    Map<String, Object> map = new HashMap<>();
    if(comment.getId() == Long.parseLong(authenticatedUserid)){
      boardService.deleteBoardComment(cno,authenticatedUserid);
      map.put("code",1);
      map.put("msg","해당 댓글 삭제를 완료하였습니다.");
    }else{
      map.put("code",2);
      map.put("msg","본인이 작성한 댓글만 삭제할 수 있습니다.");
    }
    return map;
  }
  @PatchMapping("/comment")
  public Map<String, Object> updateComment(@RequestBody BoardCommentDTO comment, @RequestAttribute String authenticatedUserid){
    Map<String, Object> map = new HashMap<>();

    BoardCommentDTO dto = boardService.selectBoardComment(comment.getCno());
    if(dto.getId() == Long.parseLong(authenticatedUserid)){
      comment.setId(Long.parseLong(authenticatedUserid));
      boardService.updateBoardComment(comment);
      map.put("comment",boardService.selectBoardComment(comment.getCno()));
      map.put("msg","댓글 수정 완료하였습니다.");
    }else{
      map.put("msg","댓글 수정 실패하였습니다.");
    }

    return map;
  }

  @PostMapping("/write")
  public Map<String,Object> boardWrite(@RequestAttribute String authenticatedUserid, @RequestPart("params") String params, @RequestPart(value = "file", required = false)MultipartFile[] files) throws IOException {
    Map<String, Object> map = new HashMap<>();
    if(authenticatedUserid == null){
      map.put("msg","로그인 하셔야 이용하실수 있습니다.");
      map.put("code",3);
      return map;
    }
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String,String> paramsMap = objectMapper.readValue(params, new TypeReference<Map<String, String>>() {});
    System.out.println(paramsMap);

    BoardDTO board = new BoardDTO();
    board.setId(Long.parseLong(authenticatedUserid));
    board.setTitle(paramsMap.get("title"));
    board.setContent(paramsMap.get("content"));



    //파일 업로드
    File root = new File("c:\\fileupload");
    //해당 경로가 있는 지 없는지 체크해서 없으면 해당 경로를 생성
    if(!root.exists()) root.mkdirs();

    List<BoardFileDTO> fileList = new ArrayList<>();
    if(files != null){
      for(MultipartFile file : files){
        if(file.isEmpty()){
          continue;
        }
        // 업로드할 파일명
        String fileName = file.getOriginalFilename();
        // 파일 저장할 경로 완성
        String filePath = root + File.separator + fileName;
        // 실제 파일 저장 부분
        file.transferTo(new File(filePath));
        BoardFileDTO fileDTO = new BoardFileDTO();
        fileDTO.setFpath(filePath);
        fileList.add(fileDTO);
      }
      //게시글 데이터베이스에 추가
      int bno = boardService.selectBoardNo();
      board.setBno(bno);
      fileList.forEach(item -> item.setBno(bno));
      int count = boardService.insertBoard(board,fileList);
      if(count != 0){
        map.put("bno",bno);
        map.put("code",1);
        map.put("msg","게시글 쓰기 성공");
      } else{
        map.put("code",2);
        map.put("msg","게시글 쓰기 실패");
      }

      return map;
    }

    return map;
  }

  @GetMapping("/download/{fno}")
  public ResponseEntity<Resource> fileDownload(@PathVariable int fno, HttpServletResponse response){
    //1. 파일 정보를 DB로 부터 읽어옴
    BoardFileDTO fileDTO = boardService.selectFile(fno);
    File file = new File(fileDTO.getFpath());
    String encodingFileName = URLEncoder.encode(fileDTO.getFileName(), StandardCharsets.UTF_8);

    String contentDisposition = "attachement;filename="+encodingFileName;
    Resource resource = new FileSystemResource(file);

    return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body(resource);

    /*
    //기존 방식
    //2. 스트림 연결해서 클라이언트에게 전송(response 헤더 설정)
    //2-1. 헤더 설정(인코딩 타입, 파일명, 사이즈)
    response.setHeader("Content-Disposition","attachement;filename="+encodingFileName);
    response.setHeader("Content-Transfer-Encoding","binary");
    response.setContentLength((int)file.length());




    //2-2. fis로 읽어와서 bos로 전송
    try(FileInputStream fis = new FileInputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
        byte[] buffer = new byte[1024*1024];
        while(true){
          int size = fis.read(buffer);
          if(size == -1) break;
          bos.write(buffer,0,size);
          bos.flush();
        }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e){
      e.printStackTrace();
    }*/
  }

  @PostMapping("/comment")
  public Map<String,Object> boardCommentWrite(@RequestBody BoardCommentDTO commentDTO, @RequestAttribute String authenticatedUserid){
    Map<String, Object> map = new HashMap<>();
    commentDTO.setId(Long.parseLong(authenticatedUserid));

    try {
      boardService.insertBoardComment(commentDTO);
      map.put("code",1);
      map.put("msg","댓글 추가 완료");
      map.put("commentList",boardService.selectCommentList(commentDTO.getBno(),1));
    } catch (Exception e) {
      map.put("code",2);
      map.put("msg","댓글 추가 실패");
    }
    return map;
  }
}






