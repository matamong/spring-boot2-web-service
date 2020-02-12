package com.gameduos.springboot.web.controller;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.dto.LevelUpRequestDto;
import com.gameduos.springboot.web.service.ReferralCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/referralCode")
public class ReferralCodeRestController {

    private final ReferralCodeService referralCodeService;

    @PostMapping
    public ResponseEntity<?> postReferralCode(@SocialUser User user) {
        System.out.println("session user ==========" + user.getEmail());
        return referralCodeService.createReferralCode(user);
    }

}
