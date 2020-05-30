package com.gameduos.springboot.web.domain.comment;

import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByBoardOrderByIdDesc(Board board, Pageable pageable);
    List<Comment> findAllByBoardOrderByIdAsc(Board board);
    List<User> findUserByBoardOrderByIdDesc(Board board);
}
