package com.kh.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class AuthRequest {
  @NotBlank(message = "사용자 아이디는 비워둘 수 없습니다.")
  private String userid;
  @NotBlank(message = "비밀번호는 비워둘 수 없습니다.")
  private String password;
  
  private String username;//선택사항
}
