package com.gameduos.springboot.web.domain.point;

import com.gameduos.springboot.web.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {
    List<Point> findByUser(User user);
}
