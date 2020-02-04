package com.gameduos.springboot.web.domain.point;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gameduos.springboot.web.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Point {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    private int point;

    @Column
    @Enumerated(EnumType.STRING)
    private PointType pointType;

    @Column
    private LocalDateTime pointGiveDate;

    @Builder
    public Point(User user, int point, PointType pointType, LocalDateTime pointGiveDate) {
        this.user = user;
        this.point = point;
        this.pointType = pointType;
        this.pointGiveDate = pointGiveDate;
    }



}
