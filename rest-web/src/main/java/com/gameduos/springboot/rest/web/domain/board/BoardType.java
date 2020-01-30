package com.gameduos.springboot.rest.web.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BoardType {
    NOTICE("BOARD_NOTICE", "공지사항"),
    FREE("BOARD_FREE", "자유게시판");

    private final String key;
    private final String value;
}
