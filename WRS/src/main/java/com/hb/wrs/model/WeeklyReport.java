package com.hb.wrs.model;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "weekly_reports")
@RequiredArgsConstructor
@Getter
@Setter
public class WeeklyReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @ManyToOne
    private Employee employee;

    @ManyToOne
    private Project project;

    @Column(name = "planned_completion_date")
    private Date plannedCompletionDate;

    @Column(name = "actual_completion_date")
    private Date actualCompletionDate;

    @Column(name = "deliverables")
    private String deliverables;

    @Column(name = "report_created_datetime")
    private LocalDateTime reportCreatedDateTime;

    @PrePersist
    public void prePersist() {
        this.reportCreatedDateTime = LocalDateTime.now();
    }
}
