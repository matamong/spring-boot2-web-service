package com.gameduos.springboot.web.domain.referralCode;

import com.gameduos.springboot.web.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReferralCodeRepository extends JpaRepository<ReferralCode, String> {
    Optional<ReferralCode> findByReferralCodeAndAvailable(String code, char available);
    Page<ReferralCode> findAllByUserId(Long userId, Pageable pageable);

    @EntityGraph(attributePaths = "user") //fetchType.eager로 강제변환
    ReferralCode findByUseUserId (Long userId);

}
