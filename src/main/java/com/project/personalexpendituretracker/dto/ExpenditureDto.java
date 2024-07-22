package com.project.personalexpendituretracker.dto;

import java.time.LocalDate;

public class ExpenditureDto {

    private int id;
    private String userName;
    private int catCode;
    private double amount;
    private LocalDate spentOn;
    private String description;
    private int paymentModeCode;
    private String remarks;
    private String tags;

    public ExpenditureDto() {
    }

    public ExpenditureDto(int id, String userName, int catCode, double amount, LocalDate spentOn, String description,
            int paymentModeCode, String remarks, String tags) {
        this.id = id;
        this.userName = userName;
        this.catCode = catCode;
        this.amount = amount;
        this.spentOn = spentOn;
        this.description = description;
        this.paymentModeCode = paymentModeCode;
        this.remarks = remarks;
        this.tags = tags;
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

    public int getCatCode() {
        return catCode;
    }

    public void setCatCode(int catCode) {
        this.catCode = catCode;
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

    public int getPaymentModeCode() {
        return paymentModeCode;
    }

    public void setPaymentModeCode(int paymentModeCode) {
        this.paymentModeCode = paymentModeCode;
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

    @Override
    public String toString() {
        return "ExpenditureDto [id=" + id + ", userName=" + userName + ", catCode=" + catCode + ", amount=" + amount
                + ", spentOn=" + spentOn + ", description=" + description + ", paymentModeCode=" + paymentModeCode
                + ", remarks=" + remarks + ", tags=" + tags + "]";
    }

}