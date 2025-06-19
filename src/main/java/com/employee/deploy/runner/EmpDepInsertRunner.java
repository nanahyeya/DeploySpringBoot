package com.employee.deploy.runner;

import com.employee.deploy.entity.Department;
import com.employee.deploy.entity.Employee;
import com.employee.deploy.repository.DepartmentRepository;
import com.employee.deploy.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Profile("local")
@Slf4j
public class EmpDepInsertRunner implements ApplicationRunner {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    //    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        Department department1 = new Department();
//        department1.setDepartmentName("HR");
//        department1.setDepartmentDescription("performs human resource management functions");
//
//        Department department2 = new Department();
//        department2.setDepartmentName("Marketing");
//        department2.setDepartmentDescription("creates strategies for selling its company's products");
//
//        Department department3 = new Department();
//        department3.setDepartmentName("Sales");
//        department3.setDepartmentDescription("identifies sales goals and objectives and prepares a sales plan");
//
//        departmentRepository.saveAll(List.of(department1, department2, department3));
//
//        Employee employee1 = new Employee();
//        employee1.setFirstName("John");
//        employee1.setLastName("Smith");
//        employee1.setEmail("John@company.com");
//        employee1.setDepartment(department1);
//
//        Employee employee2 = new Employee();
//        employee2.setFirstName("Sarah");
//        employee2.setLastName("Johnson");
//        employee2.setEmail("Sarah@company.com");
//        employee2.setDepartment(department2);
//
//        Employee employee3 = new Employee();
//        employee3.setFirstName("Emily");
//        employee3.setLastName("Brown");
//        employee3.setEmail("Emilyh@company.com");
//        employee3.setDepartment(department3);
//
//        employeeRepository.saveAll(List.of(employee1,employee2,employee3));
//    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        try {
            List<Department> departments = createInitialDepartments();
            List<Employee> employees = createInitialEmployees(departments);

            departmentRepository.saveAll(departments);
            employeeRepository.saveAll(employees);

            log.info("Successfully inserted {} departments and {} employees",
                    departments.size(), employees.size());
        } catch (Exception e) {
            log.error("Failed to initialize sample data", e);
            throw new DataInitializationException("Failed to initialize sample data", e);
        }
    }

    private List<Department> createInitialDepartments() {
        return List.of(
                Department.builder()
                        .departmentName("HR")
                        .departmentDescription("Performs human resource management functions")
                        .build(),

                Department.builder()
                        .departmentName("Marketing")
                        .departmentDescription("Creates strategies for selling company products")
                        .build(),

                Department.builder()
                        .departmentName("Sales")
                        .departmentDescription("Identifies sales goals and prepares sales plans")
                        .build()
        );
    }

    private List<Employee> createInitialEmployees(List<Department> departments) {
        return List.of(
                Employee.builder()
                        .firstName("John")
                        .lastName("Smith")
                        .email("john@company.com")
                        .department(departments.get(0)) // HR
                        .build(),

                Employee.builder()
                        .firstName("Sarah")
                        .lastName("Johnson")
                        .email("sarah@company.com")
                        .department(departments.get(1)) // Marketing
                        .build(),

                Employee.builder()
                        .firstName("Emily")
                        .lastName("Brown")
                        .email("emily@company.com")
                        .department(departments.get(2)) // Sales
                        .build()
        );
    }

    public static class DataInitializationException extends RuntimeException {
        public DataInitializationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
