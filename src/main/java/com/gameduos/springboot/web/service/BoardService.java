package com.gameduos.springboot.web.service;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.controller.BoardRestController;
import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.board.BoardRepository;
import com.gameduos.springboot.web.domain.point.Point;
import com.gameduos.springboot.web.domain.point.PointRepository;
import com.gameduos.springboot.web.domain.point.PointType;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.domain.user.UserRepository;
import com.gameduos.springboot.web.dto.BoardSaveRequestDto;
import com.gameduos.springboot.web.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final PointService pointService;

    public Page<Board> findBoardList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "idx")); // <- Sort 추가
        return boardRepository.findAllByDeleted(pageable);
    }

    public ResponseEntity<?> getBoard (Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        PagedResources.PageMetadata pageMetadata = new PagedResources.PageMetadata((pageable.getPageSize()),
                boards.getNumber(), boards.getTotalElements());


        //HATESOAS가 적용. 페이징값까지 생성된 REST 데이터가 만들어짐
        PagedResources<Board> resources = new PagedResources<>(boards.getContent(), pageMetadata);

        //각 Board마다 상세정보를 불러올 수 있는 링크 추가
        resources.add(linkTo(methodOn(BoardRestController.class).getBoards(pageable)).withSelfRel());

        return ResponseEntity.ok(resources);
    }

    @Transactional
    public Board findBoardByIdx(Long idx) {
        Board board = boardRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않습니다. 게시판 번호d=" + idx));
        board.updateViewCnt();
        boardRepository.save(board);

        return board;
    }

    @Transactional
    public User findBoardUserById(Long idx) {
        Board board = boardRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않습니다. 게시판 번호d=" + idx));

        Long userId = board.getUser().getId();

        User boardUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. 유저 id = " + userId));

        return boardUser;
    }

    @Transactional
    public ResponseEntity<?> save (BoardSaveRequestDto requestDto) {
        requestDto.setCreatedDateNow();
        pointService.boardPointSave(requestDto.getUser());
        boardRepository.save(requestDto.toEntity());

        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<?> update (Long idx, BoardUpdateRequestDto requestDto) {
        requestDto.setUpdatedDateNow();

        Board entity = boardRepository.getOne(idx);

        entity.update(Board.builder()
                .boardType(requestDto.getBoardType())
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .updatedDate(requestDto.getUpdatedDate())
                .build());

        boardRepository.save(entity);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> delete (Long idx) {
        Board board = boardRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시판이 존재하지 않습니다. 게시판 번호d=" + idx));

        board.delete(board);
        boardRepository.save(board);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }


}
