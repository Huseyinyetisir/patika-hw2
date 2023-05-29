package org.example;

import org.example.model.Customer;
import org.example.model.Invoice;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Customer> customers = createCustomers();

        listAllCustomers(customers);

        listCustomersWithC(customers);

        calculateTotalInvoiceAmountForJuneCustomers(customers);

        listAllInvoices(customers);

        listHighAmountInvoices(customers);

        calculateAverageAmountOfHighInvoices(customers);

        listCustomerNamesWithLowAmountInvoices(customers);

        listSectorsWithLowAverageAmountInJuneInvoices(customers);
    }

    // List all customers
    public static void listAllCustomers(List<Customer> customers) {
        System.out.println("All customers:");
        customers.forEach(System.out::println);
    }

    // List customers with 'C' in their names
    public static void listCustomersWithC(List<Customer> customers) {
        System.out.println("\nCustomers with 'C' in their names:");
        List<Customer> customersWithC = customers.stream()
                .filter(c -> c.getName().contains("C"))
                .collect(Collectors.toList());
        customersWithC.forEach(System.out::println);
    }

    // Calculate total invoice amount for customers registered in June
    public static void calculateTotalInvoiceAmountForJuneCustomers(List<Customer> customers) {
        System.out.println("\nTotal invoice amounts for customers registered in June:");
        customers.stream()
                .filter(c -> c.getRegistrationDate().getMonth() == Month.JUNE)
                .forEach(c -> {
                    double totalAmount = c.getInvoices().stream()
                            .mapToDouble(Invoice::getAmount)
                            .sum();
                    System.out.println(c.getName() + ": " + totalAmount);
                });
    }

    // List all invoices in the system
    public static void listAllInvoices(List<Customer> customers) {
        System.out.println("\nAll invoices:");
        customers.stream()
                .flatMap(c -> c.getInvoices().stream())
                .forEach(System.out::println);
    }

    // List invoices with amount above 1500TL
    public static void listHighAmountInvoices(List<Customer> customers) {
        System.out.println("\nInvoices with amount above 1500TL:");
        List<Invoice> highAmountInvoices = customers.stream()
                .flatMap(c -> c.getInvoices().stream())
                .filter(i -> i.getAmount() > 1500)
                .collect(Collectors.toList());
        highAmountInvoices.forEach(System.out::println);
    }

    // Calculate average amount of invoices above 1500TL
    public static void calculateAverageAmountOfHighInvoices(List<Customer> customers) {
        System.out.println("\nAverage amount of invoices above 1500TL:");
        double averageAmount = customers.stream()
                .flatMap(c -> c.getInvoices().stream())
                .filter(i -> i.getAmount() > 1500)
                .mapToDouble(Invoice::getAmount)
                .average()
                .orElse(0.0);
        System.out.println(averageAmount);
    }

    // List customer names with invoices below 500TL
    public static void listCustomerNamesWithLowAmountInvoices(List<Customer> customers) {
        System.out.println("\nCustomer names with invoices below 500TL:");
        List<String> customerNames = customers.stream()
                .filter(c -> c.getInvoices().stream().anyMatch(i -> i.getAmount() < 500))
                .map(Customer::getName)
                .collect(Collectors.toList());
        customerNames.forEach(System.out::println);
    }

    // List sectors with average invoice amount below 750TL for customers registered in June
    public static void listSectorsWithLowAverageAmountInJuneInvoices(List<Customer> customers) {
        System.out.println("\nSectors with average invoice amount below 750TL for June invoices:");

        customers.stream()
                .filter(c -> c.getRegistrationDate().getMonth() == Month.JUNE)
                .flatMap(c -> c.getInvoices().stream())
                .collect(Collectors.groupingBy(Invoice::getSector,
                        Collectors.averagingDouble(Invoice::getAmount)))
                .forEach((sector, averageAmount) -> {
                    if (averageAmount < 750) {
                        System.out.println("Sector: " + sector);
                    }
                });
    }

    // Helper method to create sample customers
    public static List<Customer> createCustomers() {
        List<Customer> customers = new ArrayList<>();

        // Customer 1
        Customer customer1 = new Customer("Cem", LocalDate.of(2022, Month.JANUARY, 1));
        Invoice invoice11 = new Invoice("Invoice 1", 1000, "IT");
        Invoice invoice12 = new Invoice("Invoice 2", 2000, "IT");
        customer1.addInvoice(invoice11);
        customer1.addInvoice(invoice12);
        customers.add(customer1);

        // Customer 2
        Customer customer2 = new Customer("Customer 2", LocalDate.of(2022, Month.JUNE, 15));
        Invoice invoice21 = new Invoice("Invoice 3", 500, "Finance");
        Invoice invoice22 = new Invoice("Invoice 4", 100, "Finance");
        customer2.addInvoice(invoice21);
        customer2.addInvoice(invoice22);
        customers.add(customer2);

        // Customer 3
        Customer customer3 = new Customer("Customer 3", LocalDate.of(2023, Month.MARCH, 10));
        Invoice invoice31 = new Invoice("Invoice 5", 500, "Estate");
        Invoice invoice32 = new Invoice("Invoice 6", 200, "Estate");
        customer3.addInvoice(invoice31);
        customer3.addInvoice(invoice32);
        customers.add(customer3);

        return customers;
    }
}