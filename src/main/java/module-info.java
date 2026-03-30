module com.example.ilkjavafxprojem {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;


    opens com.example.ilkjavafxprojem to javafx.fxml;
    exports com.example.ilkjavafxprojem;
    exports demo1;
}