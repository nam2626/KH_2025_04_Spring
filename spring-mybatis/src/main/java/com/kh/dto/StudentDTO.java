package com.kh.dto;

import lombok.*;
import org.apache.ibatis.type.Alias;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
@Alias("student")
public class StudentDTO {
    private String sno;
    private String sname;
    private String mno;
    private String gender;
    private double score;
}
