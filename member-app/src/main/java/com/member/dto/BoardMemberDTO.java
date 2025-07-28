package com.member.dto;

import lombok.*;
import org.apache.ibatis.type.Alias;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Alias("member")
public class BoardMemberDTO {
  private String id;
  private String passwd;
  private String userName;
  private String nickName;
}
