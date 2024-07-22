package com.project.personalexpendituretracker.dto;

public class PaymentModeDto {

    private String name;
    private String remarks;

    public PaymentModeDto() {
    }

    public PaymentModeDto(String name,String remarks) {

        this.name = name;
        this.remarks = remarks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "PaymentModeDto [name=" + name + ", remarks=" + remarks
                + "]";
    }

}
