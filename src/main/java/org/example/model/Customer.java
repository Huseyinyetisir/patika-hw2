package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Customer {
    private String name;
    private LocalDate registrationDate;
    private List<Invoice> invoices;

    public Customer(String name, LocalDate registrationDate) {
        this.name = name;
        this.registrationDate = registrationDate;
        this.invoices = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }


    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void addInvoice(Invoice invoice) {
        invoices.add(invoice);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", registrationDate=" + registrationDate +
                ", invoices=" + invoices +
                '}';
    }
}