package com.gameduos.springboot.web.dto;

import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.board.BoardType;
import com.gameduos.springboot.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardUpdateRequestDto {
    private BoardType boardType;
    private String title;
    private String subTitle;
    private String content;
    private LocalDateTime updatedDate;

    public void setUpdatedDateNow() {
        this.updatedDate = LocalDateTime.now();
    }

    @Builder
    public BoardUpdateRequestDto (BoardType boardType, String title, String subTitle, User user,
                                String content, LocalDateTime updatedDate){
        this.boardType = boardType;
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.updatedDate = updatedDate;
    }

    public Board toEntity() {
        return Board.builder()
                .boardType(boardType)
                .title(title)
                .subTitle(subTitle)
                .content(content)
                .createdDate(updatedDate)
                .build();
    }

}
