package com.kh.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.File;

@Data
@Alias("file")
public class BoardFileDTO {
  private int fno;
  private int bno;
  private String fpath;

  public String getFileName(){
    return new File(fpath).getName();
  }
}
