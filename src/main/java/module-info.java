module com.example.ia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.naming;


    opens com.example.ia to javafx.fxml;
    exports com.example.ia;

    opens IA to javafx.fxml;
    exports IA;
}