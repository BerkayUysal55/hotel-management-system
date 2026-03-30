package com.example.ilkjavafxprojem;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CustomerReservationScreen
{

    private Room rooms[];

    public CustomerReservationScreen(Room rooms[]) {
        this.rooms=rooms;
    }

    public void show() {
        TextField nameField=new TextField();
        TextField idField=new TextField();
        DatePicker firstPicker = new DatePicker();
        DatePicker lastPicker = new DatePicker();

        ComboBox<Room> roomBox =new ComboBox<>();
        for (Room r : rooms) {roomBox.getItems().add(r);}

        Button addbutton=new Button("Add Reservation");

        addbutton.setOnAction(e -> {
            Room selectedRoom=roomBox.getValue();

            if (!selectedRoom.addCheck(firstPicker.getValue(), lastPicker.getValue())) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Room is already reserved for this date range.");
                alert.show();
                return;
            }

            if (nameField.getText().isEmpty() || idField.getText().isEmpty() || firstPicker.getValue() == null || lastPicker.getValue() == null || roomBox.getValue() == null)
                return;

            Customer c = new Customer(nameField.getText(), idField.getText(), firstPicker.getValue());

            Reservation r = new Reservation(c, firstPicker.getValue(), lastPicker.getValue());

            roomBox.getValue().addReservation(r);
            ReservationFileManager.writeReservations(rooms);

            Alert success=new Alert(Alert.AlertType.INFORMATION);
            success.setContentText("Reservation added and saved!");
            success.show();
        });

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(new Label("Customer Name: "), 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(new Label("Customer ID: "), 0, 1);
        grid.add(idField, 1, 1);
        grid.add(new Label("Start Date: "), 0, 2);
        grid.add(firstPicker, 1, 2);
        grid.add(new Label("End Date: "), 0, 3);
        grid.add(lastPicker, 1, 3);
        grid.add(new Label("Room: "), 0, 4);
        grid.add(roomBox, 1, 4);
        grid.add(addbutton, 1, 5);

        Scene scene = new Scene(grid, 400, 350);
        Stage stage = new Stage();
        stage.setTitle("Customer / Reservation Management");
        stage.setScene(scene);
        stage.show();
    }
}
