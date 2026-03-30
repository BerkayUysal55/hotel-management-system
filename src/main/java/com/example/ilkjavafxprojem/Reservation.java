package com.example.ilkjavafxprojem;



import java.time.LocalDate;

public class Reservation {

    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;

    public Reservation(Customer customer, LocalDate startDate, LocalDate endDate) //constructor
    {
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
    }



    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getStartDate() {               //Getters
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
