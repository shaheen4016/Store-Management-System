package com.prologiccreations.storemanagement.model.data;

import com.prologiccreations.storemanagement.model.config.Customer;
import com.prologiccreations.storemanagement.model.super_classes.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Order extends AuditableEntity {

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "CustomerID")
    private Customer customer;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OrderDate")
    private LocalDate orderDate;

    @Column(name = "TotalAmount")
    private double totalAmount;
}