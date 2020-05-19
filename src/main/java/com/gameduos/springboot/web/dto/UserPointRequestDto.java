package com.gameduos.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserPointRequestDto {
    private Long userId;
    private int point;

}
