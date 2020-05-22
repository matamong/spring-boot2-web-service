package com.gameduos.springboot.web.domain.userGame;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gameduos.springboot.web.domain.Game.Game;
import com.gameduos.springboot.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class UserGame {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Game game;

    @Column
    private String gameNickname;

    @Column
    private int matchingAgreed;

    @Column
    private int profileAgreed;

    @Column
    private int snsAgreed;

    @Lob
    private String content;

    public void updateMatchingAgreed(int matchingAgreed){
        this.matchingAgreed = matchingAgreed;
    }

    public void updateProfileAgreed(int profileAgreed){
        this.profileAgreed = profileAgreed;
    }

    public void updateSnsAgreed(int snsAgreed){
        this.snsAgreed = snsAgreed;
    }

    @Builder
    public UserGame(User user, Game game, String gameNickname, int matchingAgreed,
                    int profileAgreed, int snsAgreed, String content){
        this.user = user;
        this.game = game;
        this.gameNickname = gameNickname;
        this.matchingAgreed = matchingAgreed;
        this.profileAgreed = profileAgreed;
        this.snsAgreed = snsAgreed;
        this.content = content;
    }
}
