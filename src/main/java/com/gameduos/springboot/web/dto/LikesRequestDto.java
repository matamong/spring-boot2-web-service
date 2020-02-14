package com.gameduos.springboot.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LikesRequestDto {
    private Long boardIdx;
}
