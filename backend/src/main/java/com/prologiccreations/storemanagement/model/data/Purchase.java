package com.prologiccreations.storemanagement.model.data;

import com.prologiccreations.storemanagement.model.config.Product;
import com.prologiccreations.storemanagement.model.super_classes.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Purchase extends AuditableEntity {

    private String supplier;
    private String challanNo;
    @ManyToMany(cascade = CascadeType.REFRESH)
    private List<Product> product;
    private LocalDate purchaseDate;
    private int quantity;
    private double totalAmount;

}
