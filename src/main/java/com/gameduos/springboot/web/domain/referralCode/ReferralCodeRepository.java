package com.gameduos.springboot.web.domain.referralCode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReferralCodeRepository extends JpaRepository<ReferralCode, String> {
    Optional<ReferralCode> findByReferralCodeAndAvailable(String code, char available);
}
