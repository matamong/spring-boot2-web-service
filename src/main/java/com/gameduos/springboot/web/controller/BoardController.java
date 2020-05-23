package com.gameduos.springboot.web.controller;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.service.BoardService;
import com.gameduos.springboot.web.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("/list")
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardList", boardService.findBoardList(pageable));
        return "board/list";
    }

    @GetMapping({"", "/"})
    public String boardDetail(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
        // fetchType.lazy 로 불러온 entity 는 연관된 엔티티까지 담아서 thymeleaf 에 보내지 않으니 연관된 entity 를
        // List에 따로 담아서 보내주기로...
        model.addAttribute("board", boardService.findBoardByIdx(idx));
        model.addAttribute("boardUser", boardService.findBoardUserById(idx));
        model.addAttribute("commentInfoList", commentService.getCommentInfo(idx));

        return "board/detail";
    }

    @GetMapping("/editForm")
    public String boardEditForm(@RequestParam(value = "idx") Long idx, @SocialUser User user, Model model){
        User boardAuthor = boardService.findBoardUserById(idx);

        if(!user.getId().equals(boardAuthor.getId())){
            throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
        }
        model.addAttribute("board", boardService.findBoardByIdx(idx));

        return "board/editForm";
    }

    @GetMapping("/form")
    public String boardForm() {
        return "board/form";
    }
}
