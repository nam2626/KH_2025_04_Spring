package com.kh.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("comment")
public class BoardCommentDTO {
  private int cno;
  private int bno;
  private Long id;
  private String username;
  private String cdate;
  private String content;
  private int clike;
  private int chate;
}
