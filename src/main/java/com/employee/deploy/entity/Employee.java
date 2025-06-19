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
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(name = "first_name")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    //@Column(name = "last_name")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_id", nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "department_id")
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}