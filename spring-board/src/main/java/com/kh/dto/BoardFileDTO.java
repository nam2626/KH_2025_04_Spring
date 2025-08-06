package com.kh.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("file")
public class BoardFileDTO {
  private int fno;
  private int bno;
  private String fpath;

}
