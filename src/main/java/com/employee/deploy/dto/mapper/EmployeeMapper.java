package com.employee.deploy.dto.mapper;

import com.employee.deploy.dto.EmployeeDto;
import com.employee.deploy.entity.Employee;

public class EmployeeMapper {

//    public static EmployeeDto mapToEmployeeDto(Employee employee){
//        return new EmployeeDto(
//                employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail(),
//                employee.getDepartment().getId()
//        );
//    }
//
//    public static EmployeeDto mapToEmployeeDepartmentDto(Employee employee){
//        return new EmployeeDto(
//                employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail(),
//                DepartmentMapper.mapToDepartmentDto(employee.getDepartment())
//        );
//    }
//
//    public static Employee mapToEmployee(EmployeeDto employeeDto){
//         Employee employee = new Employee();
//         employee.setId(employeeDto.getId());
//         employee.setFirstName(employeeDto.getFirstName());
//         employee.setLastName(employeeDto.getLastName());
//         employee.setEmail(employeeDto.getEmail());
//         return employee;
//    }

    public static EmployeeDto toDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .departmentId(employee.getDepartment().getId())
                .build();
    }

    public static EmployeeDto toDtoWithDepartment(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .departmentId(employee.getDepartment().getId())
                .departmentDto(DepartmentMapper.toDto(employee.getDepartment()))
                .build();
    }

    public static Employee toEntity(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .build();
    }

}