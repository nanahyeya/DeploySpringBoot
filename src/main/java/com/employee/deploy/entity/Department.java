package com.employee.deploy.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @Column(name = "department_description")
    private String departmentDescription;
}