package org.example.model;

import java.time.LocalDate;

public class Invoice {
    private String name;
    private double amount;
    private String sector;

    public Invoice(String name, double amount,String sector) {
        this.name = name;
        this.amount = amount;
        this.sector = sector;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }
    public String getSector() {
        return sector;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}