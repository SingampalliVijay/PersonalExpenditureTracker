package com.project.personalexpendituretracker.entities;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class PaymentMode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false)
    private String username;
    @Column(length = 200, nullable = false)
    private String remarks;

    @JsonIgnore
    @OneToMany(mappedBy = "paymentMode", cascade = CascadeType.ALL)
    private List<Expenditure> expenditure;

    public PaymentMode() {
    }

    public PaymentMode(int code, String name, String username, String remarks) {
        this.code = code;
        this.name = name;
        this.username = username;
        this.remarks = remarks;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public List<Expenditure> getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(List<Expenditure> expenditure) {
        this.expenditure = expenditure;
    }

    @Override
    public String toString() {
        return "PaymentMode [code=" + code + ", name=" + name + ", username=" + username + ", remarks=" + remarks
                + ", expenditure=" + expenditure + "]";
    }

}
