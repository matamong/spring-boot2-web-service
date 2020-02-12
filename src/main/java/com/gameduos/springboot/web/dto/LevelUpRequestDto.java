package com.gameduos.springboot.web.dto;

import com.gameduos.springboot.web.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LevelUpRequestDto {
    private String referralCode;
    private String nickname;
    private User user;

    public void setUser(User user){
        this.user = user;
    }
}
