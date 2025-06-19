package com.employee.deploy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    private Long id;
    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotNull(message = "Department ID cannot be null")
    private Long departmentId;

    private DepartmentDto departmentDto;

//    public EmployeeDto(Long id,String firstName,String lastName, String email) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//    }
//
//
//    public EmployeeDto(Long id,String firstName,String lastName, String email, Long departmentId) {
//        this(id,firstName,lastName,email);
//        this.departmentId = departmentId;
//    }
//
//
//    public EmployeeDto(Long id,String firstName,String lastName, String email, DepartmentDto departmentDto) {
//        this(id,firstName,lastName,email);
//        this.departmentDto = departmentDto;
//    }
}