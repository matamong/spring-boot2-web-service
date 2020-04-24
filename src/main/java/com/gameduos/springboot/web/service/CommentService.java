package com.gameduos.springboot.web.service;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.controller.BoardRestController;
import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.board.BoardRepository;
import com.gameduos.springboot.web.domain.comment.Comment;
import com.gameduos.springboot.web.domain.comment.CommentRepository;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.domain.user.UserRepository;
import com.gameduos.springboot.web.dto.CommentInfoDto;
import com.gameduos.springboot.web.dto.CommentSaveRequestDto;
import com.gameduos.springboot.web.dto.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public ResponseEntity<?> get (@PageableDefault Pageable pageable, Long boardIdx, @SocialUser User user) {
        Board board = boardRepository.findById(boardIdx)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않습니다. 게시판 번호 =" + boardIdx));

        Page<Comment> comments = commentRepository.findAllByBoardOrderByIdDesc(board,pageable);

        PagedResources.PageMetadata pageMetadata = new PagedResources.PageMetadata((pageable.getPageSize()),
                comments.getNumber(), comments.getTotalElements());
        PagedResources<Comment> resources = new PagedResources<>(comments.getContent(), pageMetadata);
        resources.add(linkTo(methodOn(BoardRestController.class).getBoards(pageable)).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @Transactional
    public List<CommentInfoDto> getCommentInfo (Long boardIdx){
        Board board = boardRepository.findById(boardIdx)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않습니다. 게시판 번호 =" + boardIdx));

        List<Comment> commentsList = commentRepository.findAllByBoardOrderByIdDesc(board);

        List<CommentInfoDto> commentInfoList = new ArrayList<>();
        for(int i =0; i < commentsList.size(); i++){
            CommentInfoDto commentInfoDto = new CommentInfoDto();

            Comment comment = commentsList.get(i);
            Long commentUserId = commentsList.get(i).getId();
            String commentUserNickname = commentsList.get(i).getUser().getNickName();

            commentInfoDto.setComment(comment);
            commentInfoDto.setCommentUserId(commentUserId);
            commentInfoDto.setCommentUserNickname(commentUserNickname);

            commentInfoList.add(commentInfoDto);
        }

        return commentInfoList;

    }

    @Transactional
    public Page<Comment> findCommentsByBoardIdx(Long boardIdx, Pageable pageable) {
        Board board = boardRepository.findById(boardIdx)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않습니다. 게시판 번호 =" + boardIdx));

        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 50, new Sort(Sort.Direction.ASC, "id")); // <- Sort 추가

        return commentRepository.findAllByBoardOrderByIdDesc(board, pageable);
    }

    public List<User> findCommentUserByBoardIdx(Long boardIdx){
        Board board = boardRepository.findById(boardIdx)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않습니다. 게시판 번호 =" + boardIdx));

        return commentRepository.findUserByBoardOrderByIdDesc(board);
    }


    @Transactional
    public ResponseEntity<?> save (CommentSaveRequestDto requestDto){
        Board board = boardRepository.findById(requestDto.getBoardIdx())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않습니다. 게시판 번호 =" + requestDto.getBoardIdx()));
        requestDto.setBoard(board);

        requestDto.setUser(requestDto.getUser());
        commentRepository.save(requestDto.toEntity());

        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<?> delete(Long commentId, User user){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. 댓글 번호 = " + commentId));

        comment.delete(comment);
        commentRepository.save(comment);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> update(CommentUpdateRequestDto requestDto){
        Comment comment = commentRepository.findById(requestDto.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. 댓글 번호 = " + requestDto.getCommentId()));

        comment.update(requestDto);
        commentRepository.save(comment);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
