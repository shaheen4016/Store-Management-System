package com.prologiccreations.storemanagement.model.config;

import com.prologiccreations.storemanagement.model.super_classes.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Employee extends AuditableEntity {

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

//    @Temporal(TemporalType.DATE)
    @Column(name = "HireDate")
    private LocalDate hireDate;

    @Column(name = "Position")
    private String position;

    @Column(name = "Salary")
    private double salary;

}
