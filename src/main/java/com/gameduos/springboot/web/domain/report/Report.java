package com.gameduos.springboot.web.domain.report;

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
public class Report {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @Column
    private int reportPoint;

    @Column
    private ReportType reportType;

    @Lob
    private String description;

    @Column
    private LocalDateTime createDate;

    @Builder
    public Report (User user, int reportPoint, ReportType reportType, String description){
        this.user = user;
        this.reportPoint = reportPoint;
        this.reportType = reportType;
        this.description = description;
        this.createDate = LocalDateTime.now();
    }
}
