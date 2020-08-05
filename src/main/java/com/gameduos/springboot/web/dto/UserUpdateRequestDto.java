package com.gameduos.springboot.web.dto;

import com.gameduos.springboot.web.domain.user.Role;
import com.gameduos.springboot.web.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String nickname;
    private User user;
    private String picture;
    private LocalDateTime updatedDate;
    private String roleString;
    private Long userId;

    public void setUser(User user){
        this.user = user;
    }
    public void setUpdatedDateNow() {
        this.updatedDate = LocalDateTime.now();
    }

}
