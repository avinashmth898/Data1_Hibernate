package com.learn.hiber.entities;

import jakarta.persistence.*;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long aadharId;

    @Column(name = "customer_name",length = 50)
    private String name;

    @Column(name = "mobile_number",length=12, unique = true)
    private String mobNo;
    private double land;

    @Lob
    private String address;
    private boolean eligible=true;
}
