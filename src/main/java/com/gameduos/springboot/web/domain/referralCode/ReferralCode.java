package com.gameduos.springboot.web.domain.referralCode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gameduos.springboot.web.domain.board.Board;
import com.gameduos.springboot.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class ReferralCode implements Serializable {

    @Id
    @Column
    private String referralCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    private LocalDateTime createdDate;

    @Column(columnDefinition = "char(1) default 'Y'")
    private char available;

    @Column
    private Long useUserId;

    @Column
    private LocalDateTime usedDate;

    public void update(ReferralCode referralCode) {
        this.available = referralCode.getAvailable();
        this.useUserId = referralCode.getUseUserId();
        this.usedDate = referralCode.getUsedDate();
    }

    @Builder
    public ReferralCode (String referralCode, User user, LocalDateTime createdDate,
                         char available, Long useUserId, LocalDateTime usedDate) {
        this.referralCode = referralCode;
        this.user = user;
        this.available = available;
        this.useUserId = useUserId;
        this.usedDate = usedDate;
        this.createdDate = createdDate;
    }
}
