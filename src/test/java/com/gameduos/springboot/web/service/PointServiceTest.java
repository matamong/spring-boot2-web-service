package com.gameduos.springboot.web.service;

import com.gameduos.springboot.web.domain.point.Point;
import com.gameduos.springboot.web.domain.point.PointRepository;
import com.gameduos.springboot.web.domain.point.PointType;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.domain.user.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PointServiceTest {

    @Autowired
    private PointService pointService;
    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private UserRepository userRepository;

    @After
    public void cleanup() throws Exception{
        pointRepository.deleteAll();
        userRepository.deleteById(2L);
    }

    @Test
    public void measurePoint_test () throws Exception {
        //given
        int pointToSubtract = 10;
        User user = User.builder()
                .nickName("test")
                .build();

        userRepository.save(user);
        pointRepository.save(Point.builder()
                .point(10)
                .user(user)
                .pointType(PointType.BOARD_POINT)
                .pointGiveDate(LocalDateTime.now())
                .build());

        //when
        boolean isPointEnough = pointService.measurePoint(pointToSubtract, user);

        //then
        assertThat(isPointEnough).isEqualTo(true);
    }

}
