package com.example.timezone.entity;

import com.example.timezone.utils.CustomDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "employee_info")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "employee_info_seq")
    @SequenceGenerator(sequenceName = "employee_info_seq",name = "employee_info_seq",allocationSize = 1)
    private long id;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "contact")
    private long contact;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    @Column(name = "doj")
    private LocalDate doj;

}
