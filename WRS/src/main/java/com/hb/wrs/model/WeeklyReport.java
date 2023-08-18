package com.hb.wrs.model;

import com.hb.wrs.util.ReportStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class WeeklyReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    // Many reports can be created by one employee on a single project
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // Many reports can be created on a single project
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    // Time at which the report was created
    private LocalDateTime reportCreatedDateTime;
}
