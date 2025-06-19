package com.employee.deploy.controller;

import com.employee.deploy.dto.DepartmentDto;
import com.employee.deploy.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    // Build Create or Add Department REST API
//    @PostMapping
//    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
//        DepartmentDto department = departmentService.createDepartment(departmentDto);
//        return new ResponseEntity<>(department, HttpStatus.CREATED);
//    }
//
//    // Build Get Department REST API
//    @GetMapping("{id}")
//    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId){
//        DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);
//        return ResponseEntity.ok(departmentDto);
//    }
//
//    // Build Get All Departments REST API
//    @GetMapping
//    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
//        List<DepartmentDto> departments = departmentService.getAllDepartments();
//        return ResponseEntity.ok(departments);
//    }
//
//    // Build Update Department REST API
//    @PutMapping("{id}")
//    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long departmentId,
//                                                          @RequestBody DepartmentDto updatedDepartment){
//        DepartmentDto departmentDto = departmentService.updateDepartment(departmentId, updatedDepartment);
//        return ResponseEntity.ok(departmentDto);
//    }
//
//    // Build Delete Department REST API
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){
//        departmentService.deleteDepartment(departmentId);
//        return ResponseEntity.ok("Department deleted successfully!.");
//    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        DepartmentDto savedDepartment = departmentService.createDepartment(departmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDepartment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentDto departmentDto) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, departmentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok(Map.of("message", "Department deleted successfully"));
    }

}