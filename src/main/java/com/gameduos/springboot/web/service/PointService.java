package com.gameduos.springboot.web.service;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.point.Point;
import com.gameduos.springboot.web.domain.point.PointRepository;
import com.gameduos.springboot.web.domain.point.PointType;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.dto.BoardSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PointService {
    private final PointRepository pointRepository;

    private int boardPoint = 10;
    private int loginPoint = 1;
    private int referralCodePoint = -50;

    public void boardPointSave (User user) {
        pointRepository.save(Point.builder()
                .user(user)
                .point(boardPoint)
                .pointType(PointType.BOARD_POINT)
                .pointGiveDate(LocalDateTime.now())
                .build());
    }

    public void LoginPointSave (User user) {
        pointRepository.save(Point.builder()
                .user(user)
                .point(loginPoint)
                .pointType(PointType.LOGIN_POINT)
                .pointGiveDate(LocalDateTime.now())
                .build());
    }

    public void ReferralPointCut (@SocialUser User user) {
        pointRepository.save(Point.builder()
                .user(user)
                .point(referralCodePoint)
                .pointType(PointType.CODE_CREATE)
                .pointGiveDate(LocalDateTime.now())
                .build());
    }

    public boolean measureReferralPoint (User user) {
        boolean isPointEnough = false;
        int totalPoint = 0;

        List<Point> pointList = pointRepository.findByUser(user);

        for(Point point : pointList) {
            totalPoint += point.getPoint();
        }
        if(totalPoint >= referralCodePoint) {
            isPointEnough = true;
        }
        return isPointEnough;
    }

    public boolean measurePoint (int pointToSubtract, User user) {
        boolean isPointEnough = false;
        int totalPoint = 0;

        List<Point> pointList = pointRepository.findByUser(user);

        for(Point point : pointList) {
            totalPoint += point.getPoint();
        }
        if(totalPoint >= pointToSubtract) {
            isPointEnough = true;
        }
        return isPointEnough;
    }
}
