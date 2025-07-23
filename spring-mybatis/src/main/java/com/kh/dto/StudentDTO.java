package com.kh.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class StudentDTO {
    private String sno;
    private String sname;
    private String mno;
    private String gender;
    private double score;
}
