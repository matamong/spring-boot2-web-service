package com.gameduos.springboot.web;

import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.board.BoardRepository;
import com.gameduos.springboot.web.domain.board.BoardType;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.domain.user.UserRepository;
import com.gameduos.springboot.web.resolver.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class Application implements WebMvcConfigurer {

    @Autowired
    private UserArgumentResolver userArgumentResolver;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userArgumentResolver);
    }

    @Bean
    public CommandLineRunner runner(UserRepository userRepository,
                                    BoardRepository boardRepository) throws Exception {
        return (args -> {
            User user = userRepository.save(User.builder()
            .name("matmaong")
            .email("matamong@gmail.com")
            .build());

            IntStream.rangeClosed(1, 200).forEach(index ->
                    boardRepository.save(Board.builder()
                    .title("게시글"+index)
                    .subTitle("순서"+index)
                    .content("내용"+index).createdDate(LocalDateTime.now())
                    .boardType(BoardType.FREE)
                    .user(user)
                    .build())
            );
        });
    }
}
