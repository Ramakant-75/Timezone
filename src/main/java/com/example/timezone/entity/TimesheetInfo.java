package com.example.timezone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "timesheet_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimesheetInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "timesheet_info_seq")
    @SequenceGenerator(sequenceName = "timesheet_info_seq",name = "timesheet_info_seq",allocationSize = 1)
    private long id;

    @Column(name = "employee_id")
    private String employeeId;
}
