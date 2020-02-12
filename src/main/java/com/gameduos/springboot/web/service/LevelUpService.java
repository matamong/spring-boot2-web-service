package com.gameduos.springboot.web.service;

import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.referralCode.ReferralCode;
import com.gameduos.springboot.web.domain.referralCode.ReferralCodeRepository;
import com.gameduos.springboot.web.domain.user.Role;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.domain.user.UserRepository;
import com.gameduos.springboot.web.dto.BoardUpdateRequestDto;
import com.gameduos.springboot.web.dto.LevelUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class LevelUpService {

    private final ReferralCodeRepository referralCodeRepository;
    private final UserService userService;



    @Transactional
    public ResponseEntity<?> levelUpToUser (LevelUpRequestDto requestDto, User user) {
        ReferralCode entity = referralCodeRepository.findByReferralCodeAndAvailable(requestDto.getReferralCode(),'Y')
                .orElseThrow(() -> new IllegalArgumentException("해당 코드가 존재하지않습니다. code=" + requestDto.getReferralCode()));

            entity.update(ReferralCode.builder()
                    .available('N')
                    .useUserId(user.getId())
                    .usedDate(LocalDateTime.now())
                    .build());
            referralCodeRepository.save(entity);

            userService.nicknameUpdate(user.getId(), requestDto.getNickname());
            userService.roleUpdate(user.getId(), Role.USER);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
