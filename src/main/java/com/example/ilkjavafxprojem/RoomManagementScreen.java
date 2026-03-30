package com.example.ilkjavafxprojem;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RoomManagementScreen {
    private Room rooms[];

    public RoomManagementScreen(Room rooms[]) {
        this.rooms=rooms;}

    public void show() {
        Stage stage =new Stage();
        GridPane grid =new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        Label lblSelect =new Label("Select Room:");
        ComboBox<String> cmbRooms =new ComboBox<>();

        for (int i = 0; i < rooms.length; i++) {
            Room r = rooms[i];
            cmbRooms.getItems().add("Room " + r.getRoomNumber());
        }

        Label lblType = new Label("Room Type:");
        TextField txtType = new TextField();

        Label lblPrice = new Label("Daily Price:");
        TextField txtPrice = new TextField();

        Button btnSave = new Button("Update Room");
        Label lblStatus = new Label();

            cmbRooms.setOnAction(e -> {
            int index =cmbRooms.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                Room r = rooms[index];
                txtType.setText(r.getType());
                txtPrice.setText(String.valueOf(r.getPrice()));
            }
            });

            btnSave.setOnAction(e -> {
            int index =cmbRooms.getSelectionModel().getSelectedIndex();
            if (index < 0) {
                lblStatus.setText("Please select a room first.");
                return;
            }

            try {
                Room r= rooms[index];
                r.setType(txtType.getText());
                r.setPrice(Double.parseDouble(txtPrice.getText()));
                RoomFileManager.writeRooms(rooms);
                lblStatus.setText("Saved successfully!");
            }
            catch(NumberFormatException ex)
            {
                lblStatus.setText("Price must be a number!");
            }
        });





            grid.add(lblSelect, 0, 0);
            grid.add(cmbRooms, 1, 0);
            grid.add(lblType, 0, 1);
            grid.add(txtType, 1, 1);
            grid.add(lblPrice, 0, 2);
            grid.add(txtPrice, 1, 2);
            grid.add(btnSave, 1, 3);
            grid.add(lblStatus, 1, 4);
            Scene scene=new Scene(grid, 300, 250);
            stage.setScene(scene);
            stage.setTitle("Room Management");
            stage.show();
    }
}