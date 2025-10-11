package IA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class WebsiteReportController {
    @FXML
    ComboBox cmbLostOrStolen = new ComboBox();
    @FXML
    TextField txtLostOrStolenVehicle = new TextField();
    @FXML
    TextField txtLocation = new TextField();
    @FXML
    Button btnSubmit = new Button();
    @FXML
    Button btnBack = new Button();

    public void initialize(){
        String[] reason = {"Lost", "Stolen"};
        cmbLostOrStolen.getItems().addAll(reason);
    }
    public void onClickSubmit(ActionEvent actionEvent) throws IOException {
        String lostOrStolen = (String) cmbLostOrStolen.getValue();
        String lostOrStolenVehicle = txtLostOrStolenVehicle.getText();
        String location = txtLocation.getText();

        //check if everything is filled out
        if(lostOrStolen.isEmpty() || lostOrStolenVehicle.isEmpty() || location.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Did not fill all required fields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all missing fields");
            alert.showAndWait();
        }
        else{
            //code here to send to admin/government

            //sends back to home
            FXMLLoader WebsiteHomeFxmlLoader = new FXMLLoader(IAApplication.class.getResource("WebsiteHome.fxml"));
            Scene scene = new Scene(WebsiteHomeFxmlLoader.load(), 600, 400);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();;
            stage.setScene(scene);
            stage.show();
        }
    }
    public void onClickBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader WebsiteHomeFxmlLoader = new FXMLLoader(IAApplication.class.getResource("WebsiteHome.fxml"));
        Scene scene = new Scene(WebsiteHomeFxmlLoader.load(), 600, 400);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();;
        stage.setScene(scene);
        stage.show();
    }
}
