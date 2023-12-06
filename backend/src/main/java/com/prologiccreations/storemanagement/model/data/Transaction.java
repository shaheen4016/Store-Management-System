package com.prologiccreations.storemanagement.model.data;

import com.prologiccreations.storemanagement.model.config.Employee;
import com.prologiccreations.storemanagement.model.super_classes.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Transaction extends AuditableEntity {

    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employee;

    @Column(name = "TransactionType")
    private String transactionType; // e.g., Sale, Refund, Expense

    @Column(name = "Amount")
    private double amount;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TransactionDate")
    private LocalDate transactionDate;

    @Column(name = "Description")
    private String description;

}
