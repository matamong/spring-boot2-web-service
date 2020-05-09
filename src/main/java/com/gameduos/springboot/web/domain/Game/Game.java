package com.gameduos.springboot.web.domain.Game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Game {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private GameGenre gameGenre;

    @Column
    private String picturePath;

    @Column
    private String bannerPicturePath;

    @Column
    private String publisher;

    @Column
    private String developer;

    @Builder
    public Game(String name, GameGenre gameGenre, String picturePath,
                String bannerPicturePath, String publisher, String developer){
        this.name = name;
        this.gameGenre = gameGenre;
        this.picturePath = picturePath;
        this.bannerPicturePath = bannerPicturePath;
        this.publisher = publisher;
        this.developer = developer;
    }
}
