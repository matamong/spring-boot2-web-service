package com.gameduos.springboot.web.service;

import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.point.PointType;
import com.gameduos.springboot.web.domain.referralCode.ReferralCode;
import com.gameduos.springboot.web.domain.user.Role;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.domain.user.UserRepository;
import com.gameduos.springboot.web.dto.BoardUpdateRequestDto;
import com.gameduos.springboot.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PointService pointService;

    private int userUpdatePoint = 50;

    @Transactional
    public User getUser (Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Id가 존재하지않습니다. Id = " + userId));

        return user;
    }

    @Transactional
    public ResponseEntity<?> update (UserUpdateRequestDto requestDto) {
        requestDto.setUpdatedDateNow();

        User entity = userRepository.findById(requestDto.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 Id가 존재하지 않습니다. id = " + requestDto.getUser().getId()));

        boolean isPointEnough = pointService.measurePoint(entity, userUpdatePoint);

        if(isPointEnough){
            entity.update(User.builder()
                    .nickName(requestDto.getNickname())
                    .picture(requestDto.getPicture())
                    .updatedDate(requestDto.getUpdatedDate())
                    .build()
            );

            userRepository.save(entity);
            pointService.cutPoint(entity, userUpdatePoint, PointType.NICKNAME_CHANGE);

            return new ResponseEntity<>("{}", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
    }


    @Transactional
    public void nicknameUpdate (Long userId, String nickname) {
        User entity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Id가 존재하지않습니다. Id=" + userId));

        entity.nicknameUpdate(nickname);

        userRepository.save(entity);
    }

    @Transactional
    public void roleUpdate(Long userId, Role role){
        User entity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Id가 존재하지않습니다. Id=" + userId));

        entity.roleUpdate(role);

        userRepository.save(entity);
    }

    @Transactional
    public ResponseEntity<?> delete(User user){
        User entity = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 Id가 존재하지 않습니다. Id = " + user.getId()));

        userRepository.deleteById(entity.getId());
        pointService.deleteAllUserPoint(entity);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

}
