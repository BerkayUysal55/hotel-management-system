package com.example.ilkjavafxprojem;

import java.time.LocalDate;

public class Room {

        private String type;
        private double price;
        private int roomNumber;
        private Reservation reservations[];
        private int reservationCount;

        public Room(int roomNumber) {
        this.roomNumber=roomNumber;
        reservations=new Reservation[20];
        reservationCount= 0;
        this.type ="Standard";
        this.price =1000.0;
        }

        public int getRoomNumber() {
            return roomNumber;
        } //gets room number

        public void addReservation(Reservation r)
        {
            reservations[reservationCount] = r;
            reservationCount++;
        }
        public int availabilitycheck(LocalDate start, LocalDate end) {    //checks if there exist another reservation

            boolean hasOverlap=false;

            for (int i=0;i<reservationCount;i++) {

                Reservation r=reservations[i];

                boolean overlap = (!start.isAfter(r.getEndDate()) && !end.isBefore(r.getStartDate()));
                if(overlap){
                    hasOverlap=true;

                    boolean fullyCovered = (start.isAfter(r.getStartDate()) || start.isEqual(r.getStartDate())) && (end.isBefore(r.getEndDate()) || end.isEqual(r.getEndDate()));

                    if (fullyCovered) {
                        return 2;
                    }
                }
            }

            if (hasOverlap)
                return 1;
            else
                return 0;
        }
        public Customer[] getCustomers() {
            Customer[] temp = new Customer[reservationCount];

            for (int i=0;i<reservationCount;i++) {
                temp[i]=reservations[i].getCustomer();
            }
            for (int i=0;i<temp.length -  1;i++) {
                for (int j=i+1;j<temp.length; j++) {
                    if (temp[i].compareTo(temp[j]) > 0) {
                        Customer swap = temp[i];
                        temp[i] = temp[j];
                        temp[j] = swap;
                    }
                }
            }
            return temp;
        }
    public Customer[] getCustomersForDate(LocalDate start, LocalDate end) {

        Customer temp[] =new Customer[reservationCount];
        int count = 0;

        for (int i=0;i<reservationCount;i++) {

            Reservation r = reservations[i];

            boolean overlap = (!start.isAfter(r.getEndDate()) && !end.isBefore(r.getStartDate()));

            if (overlap){
                temp[count]=r.getCustomer();
                count++;
            }
        }

        Customer  result[]  =new Customer[count];
        for (int i=0;i<count; i++) {
            result[i] = temp[i];
        }


        for (int i=0; i<result.length- 1;i++) {
            for (int j=i+ 1; j<result.length;j++) {
                if (result[i].compareTo(result[j]) > 0) {
                    Customer t = result[i];
                    result[i] =result[j];
                    result[j]= t;
                }
            }
        }

        return result;
    }

    public boolean addCheck(LocalDate start, LocalDate end) {

        for (int i =0;i<reservationCount;i++) {
            Reservation r =reservations[i];

            boolean overlap = (!start.isAfter(r.getEndDate()) && !end.isBefore(r.getStartDate()));

            if (overlap) {
                return false;
            }
        }
        return true;
    }
    public Reservation[] getReservations() {

        return reservations;
        }

    public int getReservationCount() {
        return reservationCount;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String toString() {
        return "Room " + roomNumber;
    }

}
