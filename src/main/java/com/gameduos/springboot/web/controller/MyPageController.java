package com.gameduos.springboot.web.controller;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.service.BoardService;
import com.gameduos.springboot.web.service.ReferralCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/myPage")
public class MyPageController {

    private final ReferralCodeService referralCodeService;

    @GetMapping({"", "/"})
    public String myPage(@SocialUser User user) {
        return "myPage/myPage";
    }

    @GetMapping("/referralCode")
    public String referralCode(@PageableDefault Pageable pageable, Model model, @SocialUser User user) {
        model.addAttribute("referralCodeList", referralCodeService.findBoardList(pageable));
        return "myPage/referralCode";
    }
}
