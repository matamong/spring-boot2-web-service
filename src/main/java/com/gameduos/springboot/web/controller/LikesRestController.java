package com.gameduos.springboot.web.controller;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.dto.LikesRequestDto;
import com.gameduos.springboot.web.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/likes")
public class LikesRestController {

    private final LikesService likesService;

    @PostMapping("/board")
    public ResponseEntity<?> boardLikesPost(@RequestBody LikesRequestDto requestDto, @SocialUser User user){
        return likesService.boardLikeSave(requestDto, user);
    }
}
