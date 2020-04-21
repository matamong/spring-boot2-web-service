package com.gameduos.springboot.web.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.dto.CommentUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Comment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Board board;

    @ManyToOne
    private User user;

    @Column
    private int sequence;

    @Lob
    private String content;

    @Column(columnDefinition = "int default 0")
    private int deleted;

    @Column(columnDefinition = "int default 0")
    private int updated;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime deletedDate;

    public void setCreatedDateNow() {
        this.createdDate = LocalDateTime.now();
    }

    public void update(CommentUpdateRequestDto requestDto){
        this.content = requestDto.getContent();
        this.updated = 1;
        this.createdDate = LocalDateTime.now();
    }

    public void delete(Comment comment){
        this.deleted = 1;
        this.deletedDate = LocalDateTime.now();
    }

    @Builder
    public Comment(Board board, User user, int sequence, String content){
        this.board = board;
        this.user = user;
        this.sequence = sequence;
        this.content = content;
        this.createdDate = LocalDateTime.now();
    }


}
