package com.gameduos.springboot.web.controller;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.dto.CommentSaveRequestDto;
import com.gameduos.springboot.web.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentRestController {
    private final CommentService commentService;

    @GetMapping(value = "/{boardIdx}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getComments(@PageableDefault Pageable pageable, @PathVariable("boardIdx")Long boardIdx, @SocialUser User user){
        return commentService.get(pageable, boardIdx, user);
    }

    @PostMapping
    public ResponseEntity<?> saveComments(@RequestBody CommentSaveRequestDto requestDto, @SocialUser User user){
        requestDto.setUser(user);
        return commentService.save(requestDto);
    }

}
