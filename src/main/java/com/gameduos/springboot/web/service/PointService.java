package com.gameduos.springboot.web.service;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.point.Point;
import com.gameduos.springboot.web.domain.point.PointRepository;
import com.gameduos.springboot.web.domain.point.PointType;
import com.gameduos.springboot.web.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PointService {
    private final PointRepository pointRepository;

    private int boardPoint = 100;
    private int loginPoint = 100;

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

   public void cutPoint (@SocialUser User user, int needPoint, PointType pointType) {
        pointRepository.save(Point.builder()
                .user(user)
                .point(-needPoint)
                .pointType(pointType)
                .pointGiveDate(LocalDateTime.now())
                .build());
    }

    public boolean measurePoint (User user, int needPoint) {
        if(isPointNotNull(user)){
            int totalPoint = sumPoint(user);
            return totalPointMeasure(totalPoint,needPoint);
        }
        return false;
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

    public boolean isPointNotNull(User user){
        List<Point> pointList = pointRepository.findByUser(user);

        if(user != null && !pointList.isEmpty()) {
            return true;
        }else{
            return false;
        }
    }

    public int sumPoint(User user){
        int totalPoint = 0;

        List<Point> pointList = pointRepository.findByUser(user);

        for(Point point : pointList) {
            totalPoint += point.getPoint();
        }
        return totalPoint;
    }

    public boolean totalPointMeasure(int totalPoint, int needPoint){
        if(totalPoint >= needPoint) {
            return true;
        }else{
            return false;
        }
    }
}
