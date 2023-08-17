package com.hb.wrs.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@JsonIgnoreProperties("projects")
public class Employee {
    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", name=" + name + ", designation=" + designation + ", role=" + role
                + ", projects=" + projects + "]";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @Column(name = "name")
    private String name;

    @Column(name = "designation")
    private String designation;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    public enum Role {
        SUPER_ADMIN,
        TEAM_LEADER,
        REGULAR_EMPLOYEE
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "emp_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    @JsonManagedReference
    private List<Project> projects = new ArrayList<>();



}
