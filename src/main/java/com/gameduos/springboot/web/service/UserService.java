package com.gameduos.springboot.web.service;

import com.gameduos.springboot.web.domain.referralCode.ReferralCode;
import com.gameduos.springboot.web.domain.user.Role;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

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

}
