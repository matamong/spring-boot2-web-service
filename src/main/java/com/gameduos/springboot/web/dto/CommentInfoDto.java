package com.gameduos.springboot.web.dto;

import com.gameduos.springboot.web.domain.comment.Comment;
import com.gameduos.springboot.web.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentInfoDto {
    private Comment comment;
    private String commentUserNickname;
    private Long commentUserId;
    private User commentUser;

    public void setComment(Comment comment){
        this.comment = comment;
    }

    public void setCommentUserNickname(String commentUserNickname){
        this.commentUserNickname = commentUserNickname;
    }

    public void setCommentUserId(Long commentUserId){
        this.commentUserId = commentUserId;
    }

    public void setCommentUser(User user){
        this.commentUser = user;
    }
}
