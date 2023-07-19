package com.hb.wrs.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;

// import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
// import jakarta.persistence.JoinTable;
// import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "projects")
@Getter
@Setter
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
    @JoinColumn(name = "team_leader_id")  
    private Employee teamLeader;
    //team leader is also employee so it is indirectly employee id



 @ManyToMany(mappedBy = "projects",cascade = CascadeType.ALL )
    private List<Employee> employees = new ArrayList<>();



//  @ManyToMany
//     @JoinTable(
//         name = "project_employee",
//         joinColumns = @JoinColumn(name = "project_id"),
//         inverseJoinColumns = @JoinColumn(name = "employee_id")
//     )
//     @JsonManagedReference
//     private List<Employee> employees = new ArrayList<>();


}
