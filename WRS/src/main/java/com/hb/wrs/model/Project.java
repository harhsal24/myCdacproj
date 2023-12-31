package com.hb.wrs.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

// import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonIgnore;

// import jakarta.persistence.JoinTable;
// import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "projects")
@Getter
@Setter
//@JsonIgnoreProperties("employees")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_type")
    private String projectType;

    @ManyToOne
    @JoinColumn(name = "team_leader_id",nullable = true)
    private Employee teamLeader;

    @ManyToMany(mappedBy = "projects")
    @JsonManagedReference
    private List<Employee> employees = new ArrayList<>();



    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<WeeklyReport> weeklyReports = new ArrayList<>();
}
