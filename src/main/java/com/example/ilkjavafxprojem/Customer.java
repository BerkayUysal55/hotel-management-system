package com.example.ilkjavafxprojem;

import java.time.LocalDate;

public class Customer extends Person implements Comparable<Customer> {

    private LocalDate entranceDate;

    public Customer(String name, String id, LocalDate entranceDate) { //constructor
        super(name, id);
        this.entranceDate = entranceDate;
    }

    public LocalDate getEntranceDate() { //getter entrance date
        return entranceDate;
    }


    public int compareTo(Customer c2) //compare
    {
        int nameCompare = this.getName().compareTo(c2.getName());


        if (nameCompare != 0)
            return nameCompare;

        return this.entranceDate.compareTo(c2.getEntranceDate());
    }

    public String toString() {

        return getName() ;
    }
}