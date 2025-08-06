package com.kh.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;


@Data
@Alias("board")
public class BoardDTO {
  private int bno;
  private Long id;
  private String username;
  private String title;
  private String writeDate;
  private String writeUpdateDate;
  private int bcount;
  private String content;
  private int blike;
  private int bhate;

}
