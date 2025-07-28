package com.member.controller;

import com.member.service.BoardMemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MainController {
  private BoardMemberService service;

  public MainController(BoardMemberService service) {
    this.service = service;
  }
}
