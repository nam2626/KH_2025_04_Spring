package com.kh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardMemberDTO {
  private Long id;
  private String userid;
  private String username;
  private String password;
  private String role; //ROLE_USER, ROLE_ADMIN

}
