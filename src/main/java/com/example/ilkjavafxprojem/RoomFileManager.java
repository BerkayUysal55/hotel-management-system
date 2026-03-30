package com.example.ilkjavafxprojem;

import java.io.*;

public class RoomFileManager {

    private static final String FILE_NAME = "rooms.txt";

    public static void writeRooms(Room rooms[]) { //writes room information to the rooms.txt

        try (BufferedWriter bw=new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (Room r : rooms) {
                bw.write(r.getRoomNumber() + "," + r.getType() + "," + r.getPrice());
                bw.newLine();
            }
        } catch (IOException e) {e.printStackTrace(); }
    }

    public static void readRooms(Room rooms[]) { //reads information from rooms.txt
        File f = new File(FILE_NAME);
        if (!f.exists())
            return;

        try (BufferedReader br= new BufferedReader(new FileReader(f))) {
            String line;
            while ((line= br.readLine())!=null) {
                String parts[] = line.split(",");
                if (parts.length == 3) {
                    int roomNum = Integer.parseInt(parts[0]);
                    String type = parts[1];
                    double price = Double.parseDouble(parts[2]);

                    int index=roomNum - 1;
                    if (index >=0 && index< rooms.length) {
                        rooms[index].setType(type);
                        rooms[index].setPrice(price);
                    }
                }
            }
        }

        catch (IOException e) {e.printStackTrace();}

    }


}