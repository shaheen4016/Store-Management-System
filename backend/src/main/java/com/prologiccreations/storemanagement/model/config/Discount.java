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
public class Discount extends AuditableEntity {

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "DiscountType")
    private String discountType; // e.g., Percentage, Fixed Amount

    @Column(name = "Value")
    private double value;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "StartDate")
    private LocalDate startDate;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EndDate")
    private LocalDate endDate;


}
