package com.prologiccreations.storemanagement.model.config;

import com.prologiccreations.storemanagement.model.super_classes.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Supplier extends AuditableEntity {

    @Column(name = "Name")
    private String name;

    @Column(name = "ContactName")
    private String contactName;

    @Column(name = "ContactEmail")
    private String contactEmail;

    @Column(name = "ContactPhone")
    private String contactPhone;

    @Column(name = "Address")
    private String address;

    @Column(name = "City")
    private String city;

    @Column(name = "State")
    private String state;

    @Column(name = "PostalCode")
    private String postalCode;

    @Column(name = "Country")
    private String country;

}
