package com.gameduos.springboot.rest.web.controllers;

import com.gameduos.springboot.rest.web.domain.board.Board;
import com.gameduos.springboot.rest.web.domain.board.BoardRepository;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@NoArgsConstructor
@RestController
@RequestMapping("/api/boards")
public class BoardRestController {

    private BoardRepository boardRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBoards(@PageableDefault Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        PageMetadata pageMetadata = new PageMetadata(pageable.getPageSize(), boards.getNumber(), boards.getTotalElements());
        PagedResources<Board> resources = new PagedResources<>(boards.getContent(), pageMetadata);
        resources.add(linkTo(methodOn(BoardRestController.class).getBoards(pageable)).withSelfRel());
        return ResponseEntity.ok(resources);
    }
}
