package com.gameduos.springboot.web.domain.board;

import com.gameduos.springboot.web.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);
}
