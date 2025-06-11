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

    public void setAadharId(long aadharId){
        this.aadharId=aadharId;
    }
    public long getAadharId(){
        return aadharId;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return getName();
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public double getLand() {
        return land;
    }

    public void setLand(double land) {
        this.land = land;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isEligible() {
        return eligible;
    }

    public void setEligible(boolean eligible) {
        this.eligible = eligible;
    }
    public Customer( String name, String mobNo, double land, String address, boolean eligible){
        this.name=name;
        this.mobNo=mobNo;
        this.land=land;
        this.address=address;
        this.eligible=eligible;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "aadharId=" + aadharId +
                ", name='" + name + '\'' +
                ", mobNo='" + mobNo + '\'' +
                ", land=" + land +
                ", address='" + address + '\'' +
                ", eligible=" + eligible +
                '}';
    }
}
