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
public class CommentSaveRequestDto {
    private Long boardIdx;
    private Board board;
    private User user;
    private int sequence;
    private String content;
    private LocalDateTime createdDate;

    public void setUser(User user){
        this.user = user;
    }

    public void setBoard(Board board){
        this.board = board;
    }

    @Builder
    public CommentSaveRequestDto(Board board, User user, int sequence, String content){
        this.board = board;
        this.user = user;
        this.sequence = sequence;
        this.content = content;
    }

    public Comment toEntity() {
        return Comment.builder()
                .board(board)
                .user(user)
                .sequence(sequence)
                .content(content)
                .build();
    }
}
