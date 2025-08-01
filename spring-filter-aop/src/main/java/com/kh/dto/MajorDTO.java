package com.kh.dto;

import lombok.*;
import org.apache.ibatis.type.Alias;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
@Alias("major")
public class MajorDTO {
    private String mno;
    private String mname;
}
