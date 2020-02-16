package com.gameduos.springboot.web.service;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.controller.BoardRestController;
import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.board.BoardRepository;
import com.gameduos.springboot.web.domain.comment.Comment;
import com.gameduos.springboot.web.domain.comment.CommentRepository;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.dto.CommentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
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
    public ResponseEntity<?> save (CommentSaveRequestDto requestDto){
        Board board = boardRepository.findById(requestDto.getBoardIdx())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않습니다. 게시판 번호 =" + requestDto.getBoardIdx()));
        requestDto.setBoard(board);

        requestDto.setUser(requestDto.getUser());
        commentRepository.save(requestDto.toEntity());

        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }
}
