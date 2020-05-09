package com.gameduos.springboot.web.domain.Game;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GameGenre {
    MMORPG("MMORPG", "대규모 다중 사용자 온라인 롤플레잉 게임"),
    MOBA("MOBA", "멀티플레이어 온라인 배틀 아레나"),
    FPS("FPS","1인칭 슈팅게임"),
    SIMULATOR("SIMULATOR", "시뮬레이터");

    private final String key;
    private final String value;
}
