package com.gameduos.springboot.web.domain.comment;

import com.gameduos.springboot.web.domain.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByBoardOrderByIdDesc(Board board, Pageable pageable);
}
