package com.gameduos.springboot.web.service;

import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.board.BoardRepository;
import com.gameduos.springboot.web.domain.recommend.Likes;
import com.gameduos.springboot.web.domain.recommend.LikesRepository;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.dto.LikesRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public ResponseEntity<?> boardLikeSave(LikesRequestDto requestDto, User user){
        Board board = boardRepository.findById(requestDto.getBoardIdx())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않습니다. 게시판 번호d=" + requestDto.getBoardIdx()));

        boolean isIdExistsInBoard = likesRepository.existsByBoardIdxAndUser(requestDto.getBoardIdx(), user);

        if(isIdExistsInBoard){
            Likes likes = likesRepository.findByBoardIdx(requestDto.getBoardIdx())
                    .orElseThrow(() -> new IllegalArgumentException("해당 라이크가 존재하지 않습니다. 게시판 번호d=" + requestDto.getBoardIdx()));

            changeLikes(likes, board);
            likesRepository.save(likes);
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }else{
            Likes newLikes = Likes.builder()
                    .boardIdx(requestDto.getBoardIdx())
                    .user(user)
                    .build();

            changeLikes(newLikes, board);
            likesRepository.save(newLikes);
            return new ResponseEntity<>("{}", HttpStatus.CREATED);
        }
    }

    public void changeLikes(Likes likes, Board board){
        if(likes.getLikesCheck() == 0){
            likes.makeLikes();
            increaseBoardLikesCnt(board);
        }else{
            likes.likesCancel();
            decreaseBoardLikesCnt(board);
        }
        likesRepository.save(likes);
        boardRepository.save(board);
    }

    public void increaseBoardLikesCnt(Board board){
        board.increaseLikesCnt();
        boardRepository.save(board);
    }

    public void decreaseBoardLikesCnt(Board board){
        board.decreaseLikesCnt();
        boardRepository.save(board);
    }

}
