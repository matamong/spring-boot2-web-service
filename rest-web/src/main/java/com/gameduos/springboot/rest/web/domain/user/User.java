package com.gameduos.springboot.rest.web.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column
    private SocialType socialType;

    @Column
    private String principal;

    @Column
    private String recommended;

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @Builder
    public User(Long id, String name, String email, String picture, Role role, String recommended,
                String principal, SocialType socialType, LocalDateTime createdDate, LocalDateTime updatedDate){
        this.id = id;
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.principal = principal;
        this.socialType = socialType;
        this.recommended = recommended;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
