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
public class Category {

    @Id
    @Column(length = 10, nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int code;
    @Column(length = 50, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Expenditure> expenditure;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
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

    public List<Expenditure> getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(List<Expenditure> expenditure) {
        this.expenditure = expenditure;
    }

    @Override
    public String toString() {
        return "Category [code=" + code + ", name=" + name + ", expenditure=" + expenditure + "]";
    }

}
