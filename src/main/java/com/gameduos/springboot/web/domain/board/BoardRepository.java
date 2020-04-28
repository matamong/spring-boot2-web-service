package com.gameduos.springboot.web.domain.board;

import com.gameduos.springboot.web.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);

    @Query("SELECT p FROM Board as p where p.deleted=0")
    Page<Board> findAllByDeleted(Pageable pageable);
}
