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
public class BoardSaveRequestDto {
    private BoardType boardType;
    private String title;
    private String subTitle;
    private User user;
    private String content;
    private LocalDateTime createdDate;


    @Builder
    public BoardSaveRequestDto (BoardType boardType, String title, String subTitle, User user,
                                String content, LocalDateTime createdDate){
        this.boardType = boardType;
        this.title = title;
        this.subTitle = subTitle;
        this.user = user;
        this.content = content;
        this.createdDate = createdDate;
    }

    public void setCreatedDateNow() {
        this.createdDate = LocalDateTime.now();
    }

    public Board toEntity() {
        return Board.builder()
                .boardType(boardType)
                .title(title)
                .subTitle(subTitle)
                .user(user)
                .content(content)
                .createdDate(createdDate)
                .build();
    }

}
