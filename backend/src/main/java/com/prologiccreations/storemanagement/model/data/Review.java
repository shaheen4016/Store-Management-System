package com.prologiccreations.storemanagement.model.data;

import com.prologiccreations.storemanagement.model.config.Customer;
import com.prologiccreations.storemanagement.model.config.Product;
import com.prologiccreations.storemanagement.model.super_classes.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Review extends AuditableEntity {

    @ManyToOne
    @JoinColumn(name = "ProductID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @Column(name = "Rating")
    private int rating;

    @Column(name = "Comment")
    private String comment;

}
