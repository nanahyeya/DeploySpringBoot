package com.employee.deploy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDto {
    private Long id;

    @NotBlank(message = "Department name cannot be blank")
    private String departmentName;

    private String departmentDescription;
}