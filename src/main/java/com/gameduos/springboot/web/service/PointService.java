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

    public void boardPointSave (BoardSaveRequestDto requestDto) {
        pointRepository.save(Point.builder()
                .user(requestDto.getUser())
                .point(10)
                .pointType(PointType.BOARD_POINT)
                .pointGiveDate(LocalDateTime.now())
                .build());
    }

    public void LoginPointSave (@SocialUser User user) {
        pointRepository.save(Point.builder()
                .user(user)
                .point(1)
                .pointType(PointType.LOGIN_POINT)
                .pointGiveDate(LocalDateTime.now())
                .build());
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
