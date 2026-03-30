package com.example.ilkjavafxprojem;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class EmployeeManagementScreen {

    public void show() {

        TextField nameField =new TextField();
        TextField idField =new  TextField();
        TextField userField =new TextField();
        PasswordField passField =new PasswordField();
        Label statusLabel =new Label();


        Button addBtn = new Button("Add Employee");

        addBtn.setOnAction(e -> {

            Employee emp = new Employee(nameField.getText(), idField.getText(), userField.getText(), passField.getText());
            EmployeeStore.addEmployee(emp);
            EmployeeFileManager.writeEmployee(emp);
            statusLabel.setText("Employee added successfully!");
        });

        GridPane grid =new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(new Label("Name:"), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("ID:"), 0, 1);
        grid.add(idField, 1, 1);
        grid.add(new Label("Username:"), 0, 2);
        grid.add(userField, 1, 2);
        grid.add(new Label("Password:"), 0, 3);
        grid.add(passField, 1, 3);
        grid.add(addBtn, 1, 4);
        grid.add(statusLabel, 1, 5);
        GridPane.setColumnSpan(statusLabel, 2);



        Stage stage = new Stage();
        stage.setScene(new Scene(grid, 350, 300));
        stage.setTitle("Employee Management");
        stage.show();
    }
}
