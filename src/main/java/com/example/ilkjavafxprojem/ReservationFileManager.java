package com.example.ilkjavafxprojem;

import java.io.*;
import java.time.LocalDate;

public class ReservationFileManager {

    private static final String FILE_NAME = "reservations.txt";

    public static void writeReservations(Room rooms[])  //writes reservations to the reservations.txt
    {
        try {
            FileWriter fw = new FileWriter(FILE_NAME, false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < rooms.length; i++) {
                Room r = rooms[i];
                Reservation[] resList = r.getReservations();
                int count = r.getReservationCount();

                for (int j = 0; j < count; j++) {
                    Reservation res = resList[j];

                    Customer c = res.getCustomer();

                    String line = r.getRoomNumber() + "," +  c.getName() + "," +  c.getId() + "," + res.getStartDate() + "," +  res.getEndDate();

                    bw.write(line);
                    bw.newLine();
                }
            }
            bw.close();
            System.out.println("Reservations saved.");

        } catch (IOException e) {
            System.out.println("Saving Error: " + e.getMessage());
        }
    }


    public static void readReservations(Room rooms[])   //reads reservations from reservations.txt

    {
        File f = new File(FILE_NAME);
        if (!f.exists()) return;

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 5) {
                    int roomNum = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String id = parts[2];
                    LocalDate start = LocalDate.parse(parts[3]);
                    LocalDate end = LocalDate.parse(parts[4]);


                    int roomIndex = roomNum - 1;

                    if (roomIndex >= 0 && roomIndex < rooms.length) {
                        Customer c = new Customer(name, id, start);
                        Reservation res = new Reservation(c, start, end);
                        rooms[roomIndex].addReservation(res);
                    }
                }
            }
            br.close();
            System.out.println("Reservations loaded.");

        }
        catch (IOException e) {
            System.out.println("Loading error: " + e.getMessage());
        }
    }
}