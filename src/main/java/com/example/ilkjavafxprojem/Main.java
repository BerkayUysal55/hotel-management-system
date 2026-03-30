package com.example.ilkjavafxprojem;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Main extends Application {




    public void start(Stage primaryStage) {
        EmployeeFileManager.readEmployee(); //gets information from employees.txt


        ClockPane clock = new ClockPane(); //analog clock
        clock.setPrefSize(100, 100);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {clock.setCurrentTime();}));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding: 40; -fx-background-color: #b5b5b5;");

        Label titleLabel = new Label("Hotel Management System");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-style: italic; -fx-font-weight: bold;");



        //Login Screen
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        Label userLabel = new Label("Username:");
        TextField userField = new TextField();

        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();

        Button loginButton = new Button("Login");

        Label messageLabel = new Label();

        grid.add(userLabel, 0, 0);
        grid.add(userField, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passField, 1, 1);
        grid.add(loginButton, 1, 2);



        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();


            Employee emp = EmployeeStore.findByUsername(username);

            if (emp != null && emp.login(username, password)) {
                new MainScreen().show(primaryStage);
            } else {
                messageLabel.setText("Wrong username or password!");
            }

        });

        root.getChildren().addAll(titleLabel, clock, grid, messageLabel);

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("Hotel Login Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}