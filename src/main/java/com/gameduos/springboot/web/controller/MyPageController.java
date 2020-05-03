package com.gameduos.springboot.web.controller;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.service.ReferralCodeService;
import com.gameduos.springboot.web.service.UserService;
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
    private final UserService userService;

    @GetMapping({"", "/"})
    public String myPage(Model model, @SocialUser User user) {
        model.addAttribute("userInfo", userService.getUser(user.getId()));
        return "myPage/myPage";
    }

    @GetMapping("/userUpdate")
    public String userEdit(Model model, @SocialUser User user){
        model.addAttribute("userInfo", userService.getUser(user.getId()));
        return "myPage/userEdit";

    }

    @GetMapping("/userWithdrawal")
    public String userDelete(){
        return "myPage/userWithdrawal";
    }

    @GetMapping("/referralCode")
    public String referralCode(@PageableDefault Pageable pageable, Model model, @SocialUser User user) {
        model.addAttribute("referralCodeList", referralCodeService.findReferralList(user, pageable));
        return "myPage/makeReferralCode";
    }

    @GetMapping("/certification")
    public String certificateReferralCode(Model model, @SocialUser User user) {
        model.addAttribute("socialUser", user);
        return "myPage/certification";
    }
}
