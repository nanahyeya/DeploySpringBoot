package com.employee.deploy.runner;

import com.employee.deploy.entity.Department;
import com.employee.deploy.entity.Employee;
import com.employee.deploy.repository.DepartmentRepository;
import com.employee.deploy.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("local")
public class EmpDepInsertRunner implements ApplicationRunner {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Department department1 = new Department();
        department1.setDepartmentName("HR");
        department1.setDepartmentDescription("performs human resource management functions");

        Department department2 = new Department();
        department2.setDepartmentName("Marketing");
        department2.setDepartmentDescription("creates strategies for selling its company's products");

        Department department3 = new Department();
        department3.setDepartmentName("Sales");
        department3.setDepartmentDescription("identifies sales goals and objectives and prepares a sales plan");

        departmentRepository.saveAll(List.of(department1, department2, department3));

        Employee employee1 = new Employee();
        employee1.setFirstName("John");
        employee1.setLastName("Smith");
        employee1.setEmail("John@company.com");
        employee1.setDepartment(department1);

        Employee employee2 = new Employee();
        employee2.setFirstName("Sarah");
        employee2.setLastName("Johnson");
        employee2.setEmail("Sarah@company.com");
        employee2.setDepartment(department2);

        Employee employee3 = new Employee();
        employee3.setFirstName("Emily");
        employee3.setLastName("Brown");
        employee3.setEmail("Emilyh@company.com");
        employee3.setDepartment(department3);

        employeeRepository.saveAll(List.of(employee1,employee2,employee3));
    }
}