package com.employee.deploy.dto.mapper;

import com.employee.deploy.dto.DepartmentDto;
import com.employee.deploy.entity.Department;

public class DepartmentMapper {

    // convert department jpa entity into department dto
//    public static DepartmentDto mapToDepartmentDto(Department department){
//        return new DepartmentDto(
//                department.getId(),
//                department.getDepartmentName(),
//                department.getDepartmentDescription()
//        );
//    }

    // convert department dto into department jpa entity
//    public static Department mapToDepartment(DepartmentDto departmentDto){
//        return new Department(
//                departmentDto.getId(),
//                departmentDto.getDepartmentName(),
//                departmentDto.getDepartmentDescription()
//        );
//    }

    public static DepartmentDto toDto(Department department) {
        return DepartmentDto.builder() //DepartmentDtoBuilder
                .id(department.getId())
                .departmentName(department.getDepartmentName())
                .departmentDescription(department.getDepartmentDescription())
                .build();
    }

    public static Department toEntity(DepartmentDto departmentDto) {
        return Department.builder()
                .id(departmentDto.getId())
                .departmentName(departmentDto.getDepartmentName())
                .departmentDescription(departmentDto.getDepartmentDescription())
                .build();
    }

}