package com.gameduos.springboot.web.domain.recommend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Likes implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendId;

    @Column
    private Long boardIdx;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(columnDefinition = "int default 0")
    private int likesCheck;

    public void makeLikes(){
        this.likesCheck = 1;
    }

    public void likesCancel(){
        this.likesCheck = 0;
    }

    @Builder
    public Likes(Long boardIdx, User user){
        this.boardIdx = boardIdx;
        this.user = user;
    }


}
