package com.gameduos.springboot.web.service;

import com.gameduos.springboot.web.annotation.SocialUser;
import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.referralCode.ReferralCode;
import com.gameduos.springboot.web.domain.referralCode.ReferralCodeRepository;
import com.gameduos.springboot.web.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class ReferralCodeService {

    private final ReferralCodeRepository referralCodeRepository;
    private final PointService pointService;

    @Transactional
    public Page<ReferralCode> findReferralList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10);
        return referralCodeRepository.findAll(pageable);
    }

    @Transactional
    public ResponseEntity<?> createReferralCode (User user) {
        boolean isPointEnough = pointService.measureReferralPoint(user);

        System.out.println(user.getEmail() +"의 Service isPointEnough ==================== " + isPointEnough);

        if(isPointEnough) {
            String referralCode = makeReferralCode(user);

            referralCodeRepository.save(ReferralCode.builder()
                    .referralCode(referralCode)
                    .available('Y')
                    .createdDate(LocalDateTime.now())
                    .user(user)
                    .build());

            pointService.referralPointCut(user);

            return new ResponseEntity<>("{}", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<?>  useReferralCode (String referralCode, @SocialUser User user) {
        ReferralCode entity = referralCodeRepository.findByReferralCodeAndAvailable(referralCode,'Y')
                .orElseThrow(() -> new IllegalArgumentException("해당 코드가 존재하지않습니다. code=" + referralCode));

        entity.update('N', user.getId(), LocalDateTime.now());
        referralCodeRepository.save(entity);

        return new ResponseEntity<>("{}", HttpStatus.OK);
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
