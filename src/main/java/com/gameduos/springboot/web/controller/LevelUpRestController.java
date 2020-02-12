package com.gameduos.springboot.web.controller;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.dto.LevelUpRequestDto;
import com.gameduos.springboot.web.service.LevelUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/levelUp")
public class LevelUpRestController {

    private final LevelUpService levelUpService;

    @PutMapping("/{referralCode}")
    public ResponseEntity<?> levelUpToUser(@PathVariable("referralCode")String referralCode,
                                             @RequestBody LevelUpRequestDto requestDto, @SocialUser User user) {
        requestDto.setUser(user);
        return levelUpService.levelUpToUser(requestDto, user);
    }
}
