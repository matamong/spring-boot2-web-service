package com.gameduos.springboot.web.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gameduos.springboot.web.domain.BaseTimeEntity;
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
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickName;

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
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @Column(columnDefinition = "int default 0")
    private int deleted;

    @Builder
    public User(Long id, String nickName, String email, String picture, Role role,
                String principal, SocialType socialType, LocalDateTime createdDate, LocalDateTime updatedDate){
        this.id = id;
        this.nickName = nickName;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.principal = principal;
        this.socialType = socialType;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public void nicknameUpdate(String nickName) {
        this.nickName = nickName;
    }

    public void roleUpdate(Role role) {
        this.role = role;
    }

    public void update(User user){
        this.nickName = user.getNickName();
        this.picture = user.getPicture();
        this.updatedDate = user.getUpdatedDate();
    }

    public void delete(){
        this.deleted = 1;
        this.email = this.email + "(deleted)";
        this.nickName = null;
    }
}
