package com.project.personalexpendituretracker.dto;

public class CategoryTotalAmountDto {

    private int code;
    private String name;
    private double totalAmount;

    public CategoryTotalAmountDto() {
    }

    public CategoryTotalAmountDto(int code, String name, double totalAmount) {
        this.code = code;
        this.name = name;
        this.totalAmount = totalAmount;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "CategoryTotalAmountDto [code=" + code + ", name=" + name + ", totalAmount=" + totalAmount + "]";
    }

}
