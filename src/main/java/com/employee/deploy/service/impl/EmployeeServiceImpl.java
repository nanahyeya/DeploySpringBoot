package com.employee.deploy.service.impl;

import com.employee.deploy.dto.EmployeeDto;
import com.employee.deploy.dto.mapper.EmployeeMapper;
import com.employee.deploy.entity.Department;
import com.employee.deploy.entity.Employee;
import com.employee.deploy.exception.ResourceNotFoundException;
import com.employee.deploy.repository.DepartmentRepository;
import com.employee.deploy.repository.EmployeeRepository;
import com.employee.deploy.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

//    @Override
//    @Transactional
//    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
//
//        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
//
//        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Department is not exists with id: " +
//                                employeeDto.getDepartmentId(),
//                                HttpStatus.NOT_FOUND));
//
//        employee.setDepartment(department);
//
//        Employee savedEmployee = employeeRepository.save(employee);
//        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
//    }
//
//    @Override
//    public EmployeeDto getEmployeeById(Long employeeId) {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException(
//                                "Employee is not exists with given id : " + employeeId,
//                                HttpStatus.NOT_FOUND));
//
//        return EmployeeMapper.mapToEmployeeDto(employee);
//    }
//
//    @Override
//    public List<EmployeeDto> getAllEmployees() {
//        List<Employee> employees = employeeRepository.findAll();
//        return employees.stream()
//                .map(EmployeeMapper::mapToEmployeeDto)
//                .toList();
//                //.map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
//                //.collect(Collectors.toList());
//    }
//
//    public List<EmployeeDto> getAllEmployeesDepartment() {
//        return employeeRepository.findAll()
//                .stream()
//                .map(EmployeeMapper::mapToEmployeeDepartmentDto)
//                .toList();
//    }
//
//    @Override
//    @Transactional
//    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException(
//                                "Employee is not exists with given id: " + employeeId,
//                                HttpStatus.NOT_FOUND)
//        );
//
//        employee.setFirstName(updatedEmployee.getFirstName());
//        employee.setLastName(updatedEmployee.getLastName());
//        employee.setEmail(updatedEmployee.getEmail());
//
//        Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
//                .orElseThrow(() ->
//                        new ResourceNotFoundException(
//                                "Department is not exists with id: " + updatedEmployee.getDepartmentId(),
//                                HttpStatus.NOT_FOUND
//                                ));
//
//        employee.setDepartment(department);
//
//        Employee updatedEmployeeObj = employeeRepository.save(employee);
//
//        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
//    }
//
//    @Override
//    @Transactional
//    public void deleteEmployee(Long employeeId) {
//
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException(
//                        "Employee is not exists with given id: " + employeeId,
//                        HttpStatus.NOT_FOUND)
//        );
//
//        employeeRepository.deleteById(employeeId);
//    }

    @Override
    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Department department = findDepartmentByIdOrThrow(employeeDto.getDepartmentId());
        Employee employee = EmployeeMapper.toEntity(employeeDto);
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = findEmployeeByIdOrThrow(id);
        return EmployeeMapper.toDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::toDto)
                .toList();
    }

    @Override
    public List<EmployeeDto> getAllEmployeesDepartment() {
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper::toDtoWithDepartment)
                .toList();
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = findEmployeeByIdOrThrow(id);
        Department department = findDepartmentByIdOrThrow(employeeDto.getDepartmentId());

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartment(department);

        return EmployeeMapper.toDto(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = findEmployeeByIdOrThrow(id);
        employeeRepository.delete(employee);
    }

    private Employee findEmployeeByIdOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + id,
                        HttpStatus.NOT_FOUND));
    }

    private Department findDepartmentByIdOrThrow(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with id: " + id,
                        HttpStatus.NOT_FOUND));
    }

}