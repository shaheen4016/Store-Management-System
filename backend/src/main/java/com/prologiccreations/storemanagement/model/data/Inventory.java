package com.prologiccreations.storemanagement.model.data;

import com.prologiccreations.storemanagement.model.config.Product;
import com.prologiccreations.storemanagement.model.super_classes.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Inventory extends AuditableEntity {

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private Product product;

    @Column(name = "QuantityInStock")
    private int quantityInStock;

    @Column(name = "RestockThreshold")
    private int restockThreshold;

//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LastRestockDate")
    private LocalDate lastRestockDate;

    @Column(name = "Location")
    private String location;

    @Column(name = "Notes")
    private String notes;

}
