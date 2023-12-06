package com.prologiccreations.storemanagement.model.config;

import com.prologiccreations.storemanagement.model.super_classes.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category extends AuditableEntity {

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;
}
