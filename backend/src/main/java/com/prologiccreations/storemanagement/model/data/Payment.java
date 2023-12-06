package com.prologiccreations.storemanagement.model.data;

import com.prologiccreations.storemanagement.model.super_classes.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Payment extends AuditableEntity {

    @ManyToOne
    @JoinColumn(name = "OrderID")
    private Order order;

    @Column(name = "Amount")
    private double amount;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PaymentDate")
    private LocalDate paymentDate;

    @Column(name = "PaymentMethod")
    private String paymentMethod; // e.g., Credit Card, PayPal, Cash



}
