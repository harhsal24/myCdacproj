// package com.hb.wrs.model;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;

// @Entity
// @Table(name = "employee_project")
// public class EmployeeProject {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     @JoinColumn(name = "employee_id")
//     private Employee employee;

//     @ManyToOne
//     @JoinColumn(name = "project_id")
//     private Project project;

//     // Constructors, getters, and setters
// }

