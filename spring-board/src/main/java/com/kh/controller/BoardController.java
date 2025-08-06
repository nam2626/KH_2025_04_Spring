package com.kh.controller;

import com.kh.service.BoardService;
import com.kh.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardController {
  private BoardService boardService;
  private JwtTokenProvider tokenProvider;


}






