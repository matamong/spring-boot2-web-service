package com.gameduos.springboot.web.service;

import com.gameduos.springboot.web.controller.BoardRestController;
import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.board.BoardRepository;
import com.gameduos.springboot.web.dto.BoardSaveRequestDto;
import com.gameduos.springboot.web.dto.BoardUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public Page<Board> findBoardList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "idx")); // <- Sort 추가
        return boardRepository.findAll(pageable);
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


    public Board findBoardByIdx(Long idx) {
        return boardRepository.findById(idx).orElse(new Board());
    }

    @Transactional
    public ResponseEntity<?> save (BoardSaveRequestDto requestDto) {
        requestDto.setCreatedDateNow();
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
                .subTitle(requestDto.getSubTitle())
                .content(requestDto.getContent())
                .updatedDate(requestDto.getUpdatedDate())
                .build());

        boardRepository.save(entity);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> delete (Long idx) {
        boardRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }


}
