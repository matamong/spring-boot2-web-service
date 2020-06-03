package com.gameduos.springboot.web.service;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.point.PointType;
import com.gameduos.springboot.web.domain.referralCode.ReferralCode;
import com.gameduos.springboot.web.domain.referralCode.ReferralCodeRepository;
import com.gameduos.springboot.web.domain.user.Role;
import com.gameduos.springboot.web.domain.user.User;
import com.gameduos.springboot.web.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ReferralCodeService {

    private final ReferralCodeRepository referralCodeRepository;
    private final UserService userService;
    private final PointService pointService;

    private int referralCodePoint = 50;

    @Transactional
    public User findRecommendedUser(Long useUserId){
        ReferralCode referralCode = referralCodeRepository.findByUseUserId(useUserId);

            if(referralCode != null){
                User user = userService.getUserIncludeNullUser(referralCode.getUser().getId());
                return user;
            }else{
                return null;
            }
    }


    @Transactional
    public Page<ReferralCode> findReferralList(User user, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10);
        return referralCodeRepository.findAllByUserId(user.getId(), pageable);
    }

    @Transactional
    public ResponseEntity<?> updateReferralCode(String referralCode, UserUpdateRequestDto requestDto){
        ReferralCode entity = referralCodeRepository.findByReferralCodeAndAvailable(referralCode,'Y')
                .orElseThrow(() -> new IllegalArgumentException("해당 코드가 존재하지않습니다. code=" + referralCode));

        entity.update(ReferralCode.builder()
                .available('N')
                .useUserId(requestDto.getUser().getId())
                .usedDate(LocalDateTime.now())
                .build());

        referralCodeRepository.save(entity);
        userService.roleUpdate(requestDto.getUser().getId(), Role.USER);
        userService.nicknameUpdate(requestDto.getUser().getId(), requestDto.getNickname());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
        updatedAuthorities.add(new SimpleGrantedAuthority(Role.USER.getRoleType())); //add your role here [e.g., new SimpleGrantedAuthority("ROLE_NEW_ROLE")]

        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> createReferralCode (User user) {
        boolean isPointEnough = pointService.measurePoint(user, referralCodePoint);

        if(isPointEnough) {
            String referralCode = makeReferralCode(user);

            referralCodeRepository.save(ReferralCode.builder()
                    .referralCode(referralCode)
                    .available('Y')
                    .createdDate(LocalDateTime.now())
                    .user(user)
                    .build());

            pointService.cutPoint(user, referralCodePoint, PointType.CODE_CREATE);

            return new ResponseEntity<>("{}", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
    }

    private String makeReferralCode(User user) {
        int codeLength = 8;
        char[] characterTable = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                                'V', 'W', 'X', 'Y', 'Z'};
        int tableLength = characterTable.length;

        Random random = new Random(System.currentTimeMillis());
        StringBuffer buffer = new StringBuffer();

        for(int i = 0; i < codeLength; i++) {
            buffer.append(characterTable[random.nextInt(tableLength)]);
        }
        return buffer.toString();
    }
}
