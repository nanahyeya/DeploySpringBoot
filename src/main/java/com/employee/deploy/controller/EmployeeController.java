package com.employee.deploy.controller;

import com.employee.deploy.dto.EmployeeDto;
import com.employee.deploy.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    // Build Add Employee REST API
//    @PostMapping
//    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
//        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
//        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
//    }
//
//    // Build Get Employee REST API
//    @GetMapping("{id}")
//    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
//        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
//        return ResponseEntity.ok(employeeDto);
//    }
//
//    // Build Get All Employees REST API
//    @GetMapping
//    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
//        List<EmployeeDto> employees = employeeService.getAllEmployees();
//        return ResponseEntity.ok(employees);
//    }
//
//    @GetMapping("/departments")
//    public ResponseEntity<List<EmployeeDto>> getAllEmployeesDepartment() {
//        return ResponseEntity.ok(employeeService.getAllEmployeesDepartment());
//    }
//
//
//    // Build Update Employee REST API
//    @PutMapping("{id}")
//    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
//                                                      @RequestBody EmployeeDto updatedEmployee){
//          EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
//          return ResponseEntity.ok(employeeDto);
//    }
//
//    // Build Delete Employee REST API
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
//        employeeService.deleteEmployee(employeeId);
//        return ResponseEntity.ok("Employee deleted successfully!.");
//    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/departments")
    public ResponseEntity<List<EmployeeDto>> getAllEmployeesDepartments() {
        return ResponseEntity.ok(employeeService.getAllEmployeesDepartment());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(Map.of("message", "Employee deleted successfully"));
    }

}
