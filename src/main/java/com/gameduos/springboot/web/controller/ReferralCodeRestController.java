package com.gameduos.springboot.web.controller;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.service.ReferralCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/referral")
public class ReferralCodeRestController {

    private final ReferralCodeService referralCodeService;

    @PostMapping
    public ResponseEntity<?> postReferral(@SocialUser User user) {
        System.out.println("session user ==========" + user.getEmail());
        return referralCodeService.createReferralCode(user);
    }

    @PutMapping("/{code}")
    public ResponseEntity<?> putReferral(@PathVariable("code")String code, @SocialUser User user) {
        return referralCodeService.useReferralCode(code, user);
    }

}
