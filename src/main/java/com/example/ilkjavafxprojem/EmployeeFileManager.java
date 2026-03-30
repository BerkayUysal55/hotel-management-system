package com.example.ilkjavafxprojem;

import java.io.*;

public class EmployeeFileManager {

    private static final String FILE_NAME = "employees.txt";


    public static void readEmployee() { //reads employee information from employees.txt
        File f = new File("employees.txt");


        if (!f.exists()) return;

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");


                if (parts.length==4) {
                    Employee e = new Employee(parts[1], parts[0], parts[2], parts[3]);
                    EmployeeStore.addEmployee(e);
                }
            }
            br.close();

        }
        catch (IOException e)
        {
            System.out.println("File Could not Read.");
        }
    }


    public static void writeEmployee(Employee e) {  //writes information  to employees.txt
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {
            fw.write(e.getId() + "," + e.getName() + "," + e.getUsername() + "," + e.getPassword() + "\n");

        }

        catch (IOException ex)

        {
            ex.printStackTrace();
        }
    }
}
