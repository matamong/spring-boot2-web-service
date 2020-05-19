package com.gameduos.springboot.web.domain.point;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PointType {
    ADMIN_POINT("GIVING_POINT","관리자 직접 부여 포인트"),
    LOGIN_POINT("LOGIN_POINT", "로그인 포인트"),
    BOARD_POINT("BOARD_POINT", "게시물 포인트"),
    COMMENT_POINT("COMMENT_POINT", "댓글 포인트"),
    NICKNAME_CHANGE("NICKNAME_CHANGE", "닉네임 변환 차감"),
    CODE_CREATE("CODE_CREATE", "코드 지급 차감");

    private final String key;
    private final String value;
}
