package com.employee.deploy.service.impl;

import com.employee.deploy.dto.DepartmentDto;
import com.employee.deploy.dto.mapper.DepartmentMapper;
import com.employee.deploy.entity.Department;
import com.employee.deploy.exception.ResourceNotFoundException;
import com.employee.deploy.repository.DepartmentRepository;
import com.employee.deploy.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

//    @Override
//    @Transactional
//    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
//        Department department = DepartmentMapper.mapToDepartment(departmentDto);
//        Department savedDepartment = departmentRepository.save(department);
//        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
//    }
//
//    @Override
//    public DepartmentDto getDepartmentById(Long departmentId) {
//        Department department = departmentRepository.findById(departmentId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Department is not exists with a given id: " + departmentId)
//        );
//        return DepartmentMapper.mapToDepartmentDto(department);
//    }
//
//    @Override
//    public List<DepartmentDto> getAllDepartments() {
//        List<Department> departments = departmentRepository.findAll();
//        return departments.stream()
//                .map(DepartmentMapper::mapToDepartmentDto)
//                .toList();
//                //.map((department) -> DepartmentMapper.mapToDepartmentDto(department))
//                //.collect(Collectors.toList());
//    }
//
//    @Override
//    @Transactional
//    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {
//        Department department = departmentRepository.findById(departmentId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Department is not exists with a given id:"+ departmentId)
//        );
//
//        department.setDepartmentName(updatedDepartment.getDepartmentName());
//        department.setDepartmentDescription(updatedDepartment.getDepartmentDescription());
//
//        Department savedDepartment = departmentRepository.save(department);
//
//        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
//    }
//
//    @Override
//    @Transactional
//    public void deleteDepartment(Long departmentId) {
//        departmentRepository.findById(departmentId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Department is not exists with a given id: " + departmentId)
//        );
//
//        departmentRepository.deleteById(departmentId);
//    }

    @Override
    @Transactional
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.toEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.toDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department = findDepartmentByIdOrThrow(id);
        return DepartmentMapper.toDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(DepartmentMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {
        Department department = findDepartmentByIdOrThrow(id);
        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());
        return DepartmentMapper.toDto(department);
    }

    @Override
    @Transactional
    public void deleteDepartment(Long id) {
        Department department = findDepartmentByIdOrThrow(id);
        departmentRepository.delete(department);
    }

    private Department findDepartmentByIdOrThrow(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with id: " + id,
                        HttpStatus.NOT_FOUND));
    }
}