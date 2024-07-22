package com.project.personalexpendituretracker.dto;

import java.time.LocalDate;

public class ExpenditurePostDto {

    private int catCode;
    private double amount;
    private LocalDate spentOn;
    private String description;
    private int paymentModeCode;
    private String remarks;
    private String tags;

    public ExpenditurePostDto() {
    }

    public ExpenditurePostDto(double amount,String description, LocalDate spentOn, 
             String remarks, String tags,int catCode,int paymentModeCode) {
        this.catCode = catCode;
        this.amount = amount;
        this.spentOn = spentOn;
        this.description = description;
        this.paymentModeCode = paymentModeCode;
        this.remarks = remarks;
        this.tags = tags;
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
        return "ExpenditureDto [catCode=" + catCode + ", amount=" + amount
                + ", spentOn=" + spentOn + ", description=" + description + ", paymentModeCode=" + paymentModeCode
                + ", remarks=" + remarks + ", tags=" + tags + "]";
    }

}
