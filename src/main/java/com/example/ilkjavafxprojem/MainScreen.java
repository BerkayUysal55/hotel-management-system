package com.example.ilkjavafxprojem;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class MainScreen {
    private Room rooms []= new Room[25];
    private Button roombuttons[] = new Button[25];



    private void showCustomerDetails(Customer a) //customer information
    {

        Label name = new Label("Name: " + a.getName());
        Label id = new Label("ID: " + a.getId());

        VBox box = new VBox(10, name, id);
        box.setStyle("-fx-padding: 20;");

        Stage stage = new Stage();
        stage.setScene(new Scene(box, 250, 250));
        stage.setTitle("Customer Details");
        stage.show();
    }








    public void show(Stage stage) //main screen
    {
        Button roombutton = new Button("Room Management");
        Button empbutton = new Button("Employee Management");
        Button custombutton = new Button("Customer / Reservation");

        HBox managementBox = new HBox(10, roombutton, empbutton, custombutton);
        managementBox.setAlignment(Pos.CENTER);
        custombutton.setOnAction(e -> {
            new CustomerReservationScreen(rooms).show();
        });
        empbutton.setOnAction(e -> {
            new EmployeeManagementScreen().show();
        });
        roombutton.setOnAction(e -> {
            new RoomManagementScreen(rooms).show();
        });

        for (int i = 0; i < 25; i++) {
            rooms[i] = new Room(i + 1);

        }
        ReservationFileManager.readReservations(rooms);
        RoomFileManager.readRooms(rooms);


        DatePicker startDatePicker = new DatePicker();          // date picker
        DatePicker endDatePicker = new DatePicker();
        HBox dateBox = new HBox(10,  new Label("Start Date:"), startDatePicker,  new Label("End Date:"), endDatePicker);

        dateBox.setAlignment(Pos.CENTER);
        Runnable updateRoomColors = () -> {

            if (startDatePicker.getValue() == null ||
                    endDatePicker.getValue() == null)
                return;

            for (int i = 0; i < rooms.length; i++) {

                int status = rooms[i].availabilitycheck(
                        startDatePicker.getValue(),
                        endDatePicker.getValue()
                );

                if (status == 0)
                    roombuttons[i].setStyle("-fx-background-color: green;");
                else if (status == 1)
                    roombuttons[i].setStyle("-fx-background-color: orange;");
                else
                    roombuttons[i].setStyle("-fx-background-color: red;");
            }
        };




        GridPane roomGrid = new GridPane();  //initialize grid
        roomGrid.setHgap(10);
        roomGrid.setVgap(10);
        roomGrid.setAlignment(Pos.CENTER);


        int index = 0;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {

                Button roomButton = new Button("Room " + rooms[index].getRoomNumber());
                roomButton.setPrefSize(80, 60);

                roombuttons[index] = roomButton;
                int roomIndex = index;

                roomButton.setOnAction(e -> {

                    if (startDatePicker.getValue() == null || endDatePicker.getValue() == null)
                        return;



                    Customer[] customers = rooms[roomIndex].getCustomersForDate(
                            startDatePicker.getValue(),
                            endDatePicker.getValue()
                    );


                    ListView<Customer> listView = new ListView<>();
                    for (int i = 0; i < customers.length; i++) {
                        Customer c = customers[i];
                        listView.getItems().add(c);
                    }

                    listView.setOnMouseClicked(ev -> {
                        Customer selected =
                                listView.getSelectionModel().getSelectedItem();
                        if (selected != null) {
                            showCustomerDetails(selected);
                        }
                    });

                    VBox box = new VBox(10,
                            new Label("Customers in Room " + rooms[roomIndex].getRoomNumber()),
                            listView
                    );
                    box.setAlignment(Pos.CENTER);
                    box.setStyle("-fx-padding: 20;");

                    Stage popup = new Stage();
                    popup.setScene(new Scene(box, 300, 400));
                    popup.setTitle("Room Customers");
                    popup.show();
                });



                roomGrid.add(roomButton, col, row);
                index++;
            }
        }


        startDatePicker.setOnAction(e -> updateRoomColors.run());
        endDatePicker.setOnAction(e -> updateRoomColors.run());



        VBox root = new VBox(20, managementBox, dateBox, roomGrid);

        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 20;-fx-background-color: #b5b5b5;");


        Scene scene = new Scene(root, 650, 650);
        stage.setScene(scene);
        stage.setTitle("Main Screen ");
        stage.show();
    }
}
