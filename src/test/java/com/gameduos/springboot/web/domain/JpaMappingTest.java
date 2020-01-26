package com.gameduos.springboot.web.domain;

import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.board.BoardRepository;
import com.gameduos.springboot.web.domain.board.BoardType;
import com.gameduos.springboot.web.domain.user.Role;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.domain.user.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaMappingTest {
    private final String boardTestTitle = "테스트";
    private final String email = "test@gmail.com";

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Before
    public void init() {
        User user = userRepository.save(User.builder()
                .name("matamong")
                .email(email)
                .picture("http://testPath.com/test.png")
                .recommended("root")
                .role(Role.GUEST)
                .build());

        boardRepository.save(Board.builder()
                .title(boardTestTitle)
                .subTitle("서브 타이틀")
                .content("테스트 내용")
                .boardType(BoardType.FREE)
                .user(user)
                .build());
    }

    @After
    public void tear_down() {
        boardRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void 생성_테스트 () {
        User user = userRepository.findByEmail(email);
        assertThat(user.getName()).isEqualTo("matamong");
        assertThat(user.getEmail()).isEqualTo(email);

        Board board = boardRepository.findByUser(user);
        assertThat(board.getTitle()).isEqualTo(boardTestTitle);
        assertThat(board.getSubTitle()).isEqualTo("서브 타이틀");
        assertThat(board.getContent()).isEqualTo("테스트 내용");
        assertThat(board.getBoardType()).isEqualTo(BoardType.FREE);

    }

}
