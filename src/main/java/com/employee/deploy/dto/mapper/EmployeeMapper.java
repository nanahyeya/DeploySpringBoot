package com.employee.deploy.dto.mapper;

import com.employee.deploy.dto.EmployeeDto;
import com.employee.deploy.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment().getId()
        );
    }

    public static EmployeeDto mapToEmployeeDepartmentDto(Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                DepartmentMapper.mapToDepartmentDto(employee.getDepartment())
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
         Employee employee = new Employee();
         employee.setId(employeeDto.getId());
         employee.setFirstName(employeeDto.getFirstName());
         employee.setLastName(employeeDto.getLastName());
         employee.setEmail(employeeDto.getEmail());
         return employee;
    }
}