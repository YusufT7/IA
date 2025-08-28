module com.example.ia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ia to javafx.fxml;
    exports com.example.ia;
}