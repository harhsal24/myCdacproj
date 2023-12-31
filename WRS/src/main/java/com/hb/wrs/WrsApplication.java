package com.hb.wrs;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.hb.wrs.model.Employee;
import com.hb.wrs.model.Project;
import com.hb.wrs.model.WeeklyReport;
import com.hb.wrs.repository.EmployeeRepository;
import com.hb.wrs.repository.ProjectRepository;
import com.hb.wrs.repository.WeeklyReportRepository;

@SpringBootApplication
public class WrsApplication {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public WrsApplication(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(WrsApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(
            EmployeeRepository employeeRepository,
            ProjectRepository projectRepository,
            WeeklyReportRepository weeklyReportRepository
    ) {
        return args -> {
            // Create dummy employees
            Employee employee1 = new Employee();
            employee1.setName("John Doe");
            employee1.setDesignation("Software Engineer");
            employee1.setRole(Employee.Role.TEAM_LEADER);
            employeeRepository.save(employee1);

            Employee employee2 = new Employee();
            employee2.setName("Jane Smith");
            employee2.setDesignation("Product Manager");
            employee2.setRole(Employee.Role.REGULAR_EMPLOYEE);
            employeeRepository.save(employee2);

            // Create dummy projects
            Project project1 = new Project();
            project1.setProjectName("Project 1");
            project1.setProjectType("Type 1");
            project1.setTeamLeader(employee1);
            project1.getEmployees().add(employee1); // Add employee1 to project1
            projectRepository.save(project1);

            Project project2 = new Project();
            project2.setProjectName("Project 2");
            project2.setProjectType("Type 2");
            project2.setTeamLeader(employee1);
            project2.getEmployees().add(employee1); // Add employee1 to project2
            projectRepository.save(project2);

            Project project3 = new Project();
            project3.setProjectName("Project 3");
            project3.setProjectType("Type 3");
            project3.setTeamLeader(employee2);
            project3.getEmployees().add(employee2); // Add employee2 to project3
            projectRepository.save(project3);

            // Associate employees with projects
            employee1.getProjects().add(project1);
            employee1.getProjects().add(project2);
            employeeRepository.save(employee1);

            employee2.getProjects().add(project3);
            employeeRepository.save(employee2);

            // Create dummy weekly reports
            WeeklyReport report1 = new WeeklyReport();
            report1.setEmployee(employee1);
            report1.setProject(project1);
            report1.setPlannedCompletionDate(new Date());
            report1.setActualCompletionDate(new Date());
            report1.setDeliverables("Deliverables 1");
            weeklyReportRepository.save(report1);

            WeeklyReport report2 = new WeeklyReport();
            report2.setEmployee(employee2);
            report2.setProject(project2);
            report2.setPlannedCompletionDate(new Date());
            report2.setActualCompletionDate(new Date());
            report2.setDeliverables("Deliverables 2");
            weeklyReportRepository.save(report2);

            System.out.println("Dummy data inserted successfully!");
        };
    }

}
