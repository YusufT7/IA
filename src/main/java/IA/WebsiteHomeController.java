package IA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class WebsiteHomeController {
    @FXML
    Button registerBtn = new Button();
    @FXML
    Button reportBtn = new Button();
    @FXML
    TextArea vehicleTxtA = new TextArea();

    public void onClickRegisterNewVehicle(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(IAApplication.class.getResource("IA.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }


}
