package IA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class WebsiteHomeController {
    @FXML
    Button registerBtn = new Button();
    @FXML
    Button reportBtn = new Button();
    @FXML
    TextArea vehicleTxtA = new TextArea();

    public void onClickRegisterNewVehicle(ActionEvent actionEvent) throws IOException {
        FXMLLoader IAFxmlLoader = new FXMLLoader(IAApplication.class.getResource("IA.fxml"));
        Scene scene = new Scene(IAFxmlLoader.load(), 800, 600);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    public void onClickReportVehicle(ActionEvent actionEvent) throws IOException {
        FXMLLoader WebsiteReportFxmlLoader = new FXMLLoader(IAApplication.class.getResource("WebsiteReport.fxml"));
        Scene scene = new Scene(WebsiteReportFxmlLoader.load(), 462, 400);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



}
