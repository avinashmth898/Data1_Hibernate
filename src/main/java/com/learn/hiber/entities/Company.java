package com.learn.hiber.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int company_Id;

    @Column(name = "company_name")
    private String name;

    @OneToMany(mappedBy = "company")
    private List<Product> products = new ArrayList<>();
    public int getCompany_Id() {
        return company_Id;
    }
    public void addProduct(Product product){
        product.setCompany(this);
        this.products.add(product);
    }

    public void setCompany_Id(int company_Id) {
        this.company_Id = company_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company(String name) {
        this.name = name;
    }
    public List<Product> getProducts(){
        return products;
    }
    public void setProducts(List<Product> products){
        this.products=products;
    }

    public Company() {
    }
}
