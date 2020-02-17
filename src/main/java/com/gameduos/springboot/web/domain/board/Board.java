package com.gameduos.springboot.web.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gameduos.springboot.web.domain.BaseTimeEntity;
import com.gameduos.springboot.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Board implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 500)
    private String title;

    @Column
    private String subTitle;

    @Lob
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(columnDefinition = "int default 0")
    private int viewCnt;

    @Column(columnDefinition = "int default 0")
    private int likesCnt;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    public void setCreatedDateNow() {
        this.createdDate = LocalDateTime.now();
    }

    public void setUpdatedDateNow() {
        this.updatedDate = LocalDateTime.now();
    }

    public void updateViewCnt(){
        this.viewCnt += 1;
    }

    public void increaseLikesCnt(){
        this.likesCnt += 1;
    }
    public void decreaseLikesCnt(){
        this.likesCnt -= 1;
    }

    public void update(Board board) {
        this.title = board.getTitle();
        this.subTitle = board.getSubTitle();
        this.content = board.getContent();
        this.boardType = board.getBoardType();
        this.updatedDate = LocalDateTime.now();
    }

    @Builder
    public Board(String title, String subTitle, String content, BoardType boardType, User user,
                 LocalDateTime createdDate, LocalDateTime updatedDate){
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.boardType = boardType;
        this.user = user;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

}
