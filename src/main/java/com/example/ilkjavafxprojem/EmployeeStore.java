package com.example.ilkjavafxprojem;

public class EmployeeStore
{

    private static Employee[] employees = new Employee[100]; // creates an array of employees
    private static int count = 0;

    public static void addEmployee(Employee e) { //add employee to  array
        employees[count] = e;
        count++;
    }
    public static Employee findByUsername(String username){ //checks for login
        for (int i=0;i<count;i++) {
            if (employees[i].getUsername().equals(username))
                return employees[i];
        }
        return null;
    }
}
