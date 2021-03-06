package com.gameduos.springboot.web.controller;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.user.Role;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.dto.UserPointRequestDto;
import com.gameduos.springboot.web.dto.UserUpdateRequestDto;
import com.gameduos.springboot.web.service.PointService;
import com.gameduos.springboot.web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/api")
public class AdminRestController {

    private final UserService userService;
    private final PointService pointService;


    @PutMapping("/user/point")
    public ResponseEntity<?> putUserPoint(@RequestBody UserPointRequestDto userPointRequestDto){
        User user = userService.getUser(userPointRequestDto.getUserId());

        return pointService.savePoint(user, userPointRequestDto.getPoint());
    }

    @PutMapping("/user/role")
    public ResponseEntity<?> updateUserRole(@RequestBody UserUpdateRequestDto userUpdateRequestDto){
        User user = userService.getUser(userUpdateRequestDto.getUserId());
        System.out.println(user.getId());
        return userService.updateUserAuth(user, userUpdateRequestDto.getRoleString());
    }
}
