package com.gameduos.springboot.web.dto;

import com.gameduos.springboot.web.annotation.SocialUser;
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
//    private String subTitle;
    private User user;
    private String content;
    private LocalDateTime createdDate;

    public void setUser(User user){
        this.user = user;
    }

    @Builder
    public BoardSaveRequestDto (BoardType boardType, String title, User user,
                                String content, LocalDateTime createdDate){
        this.boardType = boardType;
        this.title = title;
        this.user = user;
//        this.subTitle = subTitle;
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
                .user(user)
//                .subTitle(subTitle)
                .content(content)
                .createdDate(createdDate)
                .build();
    }

}
