package com.gameduos.springboot.web.controller;


import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.dto.CommentUpdateRequestDto;
import com.gameduos.springboot.web.dto.UserUpdateRequestDto;
import com.gameduos.springboot.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @PutMapping("/{idx}")
    public ResponseEntity<?> updateComment(@PathVariable("idx")Long idx, @RequestBody UserUpdateRequestDto requestDto, @SocialUser User user){
        requestDto.setUser(user);
        Long userId = userService.getUser(idx).getId();

        if(!user.getId().equals(userId)){
            throw new IllegalStateException("자신의 정보만 수정 할 수 있습니다.");
        }

        return userService.update(requestDto);
    }
}
