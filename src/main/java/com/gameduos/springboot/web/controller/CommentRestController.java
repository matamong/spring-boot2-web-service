package com.gameduos.springboot.web.controller;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.dto.CommentSaveRequestDto;
import com.gameduos.springboot.web.dto.CommentUpdateRequestDto;
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
    public ResponseEntity<?> saveComment(@RequestBody CommentSaveRequestDto requestDto, @SocialUser User user){
        requestDto.setUser(user);
        return commentService.save(requestDto);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@RequestBody CommentUpdateRequestDto requestDto, @SocialUser User user){
        Long userId = commentService.getCommentUserId(requestDto.getCommentId());

        if(!user.getId().equals(userId)){
            throw new IllegalStateException("자신의 댓글만 수정 할 수 있습니다.");
        }

        requestDto.setUser(user);
        return commentService.update(requestDto);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId, @SocialUser User user){
        Long userId = commentService.getCommentUserId(commentId);

        if(!user.getId().equals(userId)){
            throw new IllegalStateException("자신의 댓글만 삭제 할 수 있습니다.");
        }

        return commentService.delete(commentId);
    }


}
