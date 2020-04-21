package com.gameduos.springboot.web.dto;

import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.comment.Comment;
import com.gameduos.springboot.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentUpdateRequestDto {
    private Long commentId;
    private Long boardIdx;
    private Board board;
    private User user;
    private String content;
    private LocalDateTime updatedDate;

    public void setUser(User user){
        this.user = user;
    }
}
