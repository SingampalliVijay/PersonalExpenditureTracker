package com.project.personalexpendituretracker.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Expenditure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;
    @Column(name = "user_name", length = 30, nullable = false)
    private String userName;
    private double amount;
    @Column(name = "spent_on", nullable = false)
    private LocalDate spentOn;
    @Column(length = 100, nullable = false)
    private String description;
    @Column(length = 100, nullable = false)
    private String remarks;
    @Column(length = 100, nullable = false)
    private String tags;

    @ManyToOne
    @JoinColumn(name = "payment_mode_code")
    private PaymentMode paymentMode;

    @ManyToOne
    @JoinColumn(name = "cat_code")
    private Category category;

    public Expenditure() {
    }

    public Expenditure(String userName, double amount, LocalDate spentOn, String description, String remarks,
            String tags, PaymentMode paymentMode, Category category) {
        this.userName = userName;
        this.amount = amount;
        this.spentOn = spentOn;
        this.description = description;
        this.remarks = remarks;
        this.tags = tags;
        this.paymentMode = paymentMode;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getSpentOn() {
        return spentOn;
    }

    public void setSpentOn(LocalDate spentOn) {
        this.spentOn = spentOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expenditure [id=" + id + ", userName=" + userName + ", amount=" + amount + ", spentOn=" + spentOn
                + ", description=" + description + ", remarks=" + remarks + ", tags=" + tags + ", paymentMode="
                + paymentMode + ", category=" + category + "]";
    }

}
