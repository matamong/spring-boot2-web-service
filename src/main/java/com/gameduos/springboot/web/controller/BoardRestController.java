package com.gameduos.springboot.web.controller;

import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.board.BoardRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Api(tags = {"BoardRest"})
@RestController
@RequestMapping("/api/boards")
public class BoardRestController {

    private BoardRepository boardRepository;

    public BoardRestController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @ApiOperation(value = "게시물 조회", notes = "모든 보드 리스트를 조회한다")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBoards(@ApiParam(value = "페이징 조건", required = true) @PageableDefault Pageable pageable) {

        Page<Board> boards = boardRepository.findAll(pageable);
        PagedResources.PageMetadata pageMetadata = new PagedResources.PageMetadata((pageable.getPageSize()),
                boards.getNumber(), boards.getTotalElements());


        //HATESOAS가 적용. 페이징값까지 생성된 REST 데이터가 만들어짐
        PagedResources<Board> resources = new PagedResources<>(boards.getContent(), pageMetadata);

        //각 Board마다 상세정보를 불러올 수 있는 링크 추가
        resources.add(linkTo(methodOn(BoardRestController.class).getBoards(pageable)).withSelfRel());

        return ResponseEntity.ok(resources);

    }

    @ApiOperation(value = "게시물 저장", notes = "보드를 저장한다.")
    @PostMapping
    public ResponseEntity<?> postBoard(@ApiParam(value = "게시물", required = true) @RequestBody Board board) {
        //valid 체크
        board.setCreatedDateNow();
        boardRepository.save(board);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }


    @ApiOperation(value = "게시물 수정", notes = "보드를 수정한다.")
    @PutMapping("/{idx}")
    public ResponseEntity<?> putBoard(@ApiParam(value = "게시물 번호", required = true) @PathVariable("idx")Long idx,
                                      @ApiParam(value = "게시물", required = true) @RequestBody Board board) {
        //valid 체크
        Board persistBoard = boardRepository.getOne(idx) ;
        persistBoard.update(board);
        boardRepository.save(persistBoard);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @ApiOperation(value = "게시물 삭제", notes = "보드를 삭제한다.")
    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deleteBoard(@ApiParam(value = "게시물 번호", required = true) @PathVariable("idx")Long idx) {
        //valid 체크
        boardRepository.deleteById(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
