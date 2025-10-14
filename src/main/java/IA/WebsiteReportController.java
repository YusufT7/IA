package IA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static IA.MyConnection.conn;

public class WebsiteReportController {
    @FXML
    ComboBox <String> cmbLostOrStolen = new ComboBox<>();
    @FXML
    TextField txtSerialNumVehicle = new TextField();
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
    public void onClickSubmit(ActionEvent actionEvent) throws IOException, SQLException {
        String lostOrStolen = (String) cmbLostOrStolen.getValue();
        String lostOrStolenVehicle = txtSerialNumVehicle.getText();
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

            //update status
            String statusQuery = "UPDATE vehicles SET status=? WHERE serial_number=?";
            PreparedStatement ps = conn.prepareStatement(statusQuery);
            ps.setString(1, cmbLostOrStolen.getValue());
            ps.setString(2, txtSerialNumVehicle.getText());
            int rowsAffected = ps.executeUpdate();
            System.out.println(rowsAffected + " rows affected");
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
