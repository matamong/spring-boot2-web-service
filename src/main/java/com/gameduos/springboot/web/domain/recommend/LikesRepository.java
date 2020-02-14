package com.gameduos.springboot.web.domain.recommend;

import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Boolean existsByBoardIdxAndUser(Long boardIdx, User user);
    Optional<Likes> findByBoardIdx(Long boardIdx);
}
