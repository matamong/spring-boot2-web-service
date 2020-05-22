package com.gameduos.springboot.web.controller;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.user.Role;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.service.PointService;
import com.gameduos.springboot.web.service.ReferralCodeService;
import com.gameduos.springboot.web.service.UserService;
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
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final PointService pointService;
    private final ReferralCodeService referralCodeService;

    @GetMapping
    public String getAdminPage(Model model, @SocialUser User sessionUser) {
        return "admin/admin";
    }

    @GetMapping("/users/list")
    public String getUserList(@PageableDefault Pageable pageable, Model model){
        model.addAttribute("userList", userService.findUserList(pageable));
        return "admin/userList";
    }

    @GetMapping({"/user", "/user/"})
    public String getUserDetail(@RequestParam(value = "id", defaultValue = "0") Long id, Model model){
        model.addAttribute("userInfo", userService.getUser(id));
        model.addAttribute("userPoint", pointService.sumPoint(userService.getUser(id)));
        model.addAttribute("RecommendedBy", referralCodeService.findRecommendedUser(id));
        return "admin/userDetail";
    }

}
