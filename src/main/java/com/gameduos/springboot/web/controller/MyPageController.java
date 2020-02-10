package com.gameduos.springboot.web.controller;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {
    @GetMapping("/myPage")
    public String myPage(Model model, @SocialUser User user) {

        if (user != null) {
            System.out.println(user.getEmail());
            model.addAttribute("userEmail", user.getEmail());
        }
        return "myPage/myPage";
    }

    @GetMapping("/myPage/referralCode")
    public String referralCode(Model model, @SocialUser User user) {

        if (user != null) {
            System.out.println(user.getEmail());
            model.addAttribute("userEmail", user.getEmail());
        }
        return "myPage/referralCode";
    }
}
