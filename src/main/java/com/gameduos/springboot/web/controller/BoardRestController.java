package com.gameduos.springboot.web.controller;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.board.BoardRepository;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.dto.BoardSaveRequestDto;
import com.gameduos.springboot.web.dto.BoardUpdateRequestDto;
import com.gameduos.springboot.web.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/boards")
public class BoardRestController {

    private final BoardService boardService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBoards(@PageableDefault Pageable pageable) {
        return boardService.getBoard(pageable);
    }

    @PostMapping
    public ResponseEntity<?> postBoard(@RequestBody BoardSaveRequestDto requestDto, @SocialUser User user) {
        requestDto.setUser(user);
        return boardService.save(requestDto);
    }

    @PutMapping("/{idx}")
    public ResponseEntity<?> putBoard(@PathVariable("idx")Long idx, @RequestBody BoardUpdateRequestDto requestDto,
                                      @SocialUser User user) {
        User boardAuthor = boardService.findBoardUserById(idx);

        if(!user.getId().equals(boardAuthor.getId())){
            throw new IllegalStateException("자신의 게시글만 수정할 수 있습니다.");
        }

        return boardService.update(idx, requestDto);
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<?> deleteBoard(@PathVariable("idx")Long idx, @SocialUser User user) {
        User boardAuthor = boardService.findBoardUserById(idx);

        if(!user.getId().equals(boardAuthor.getId())){
            throw new IllegalStateException("자신의 게시글만 삭제할 수 있습니다.");
        }

        return boardService.delete(idx);
    }
}
