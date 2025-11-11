package IA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    Button editBtn = new Button();
    @FXML
    TextArea vehicleTxtA = new TextArea();

    Connection conn;

    public WebsiteHomeController() throws SQLException {
        conn = MyConnection.connectDB();
    }
    public void initialize() throws SQLException {
        //do I use addVehicle here or in IAController? or make a method for displaying?
        displayVehicles();

    }
    public void displayVehicles() throws SQLException {
        String userEmail = User.getCurrentUser().getEmail();
        int userId = User.getUserIdDB(conn, userEmail);
        String query = "SELECT vehicle_product_id, type, serial_number, status " +
                       "FROM vehicles WHERE user_id = ?";
        //clears textArea to not overlap with already outputted information
        vehicleTxtA.clear();
        //displays all vehicles currently registered by user (currently logged in)
        vehicleTxtA.appendText("Vehicle | Product ID | Serial Number | Status \n"); //oracle (appendText())
        vehicleTxtA.appendText("------------------------------------------------\n");
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, userId);
        ResultSet rs1 = ps.executeQuery();
        //chatgpt
        while (rs1.next()) {
            vehicleTxtA.appendText(String.format(
                "%s | %s | %s | %s\n",
                rs1.getString("type"),
                rs1.getString("vehicle_product_id"),
                rs1.getString("serial_number"),
                rs1.getString("status")
            ));
            //end
        }

        vehicleTxtA.setWrapText(false);
        vehicleTxtA.setScrollTop(Double.MAX_VALUE); //chatgpt
    }

    public void onClickRegisterNewVehicle(ActionEvent actionEvent) throws IOException {
        FXMLLoader IAFxmlLoader = new FXMLLoader(IAApplication.class.getResource("IA.fxml"));
        Scene scene = new Scene(IAFxmlLoader.load(), 694, 575);
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
    public void onClickEditVehicle(ActionEvent actionEvent) throws IOException {
        FXMLLoader WebsiteEditFxmlLoader = new FXMLLoader(IAApplication.class.getResource("WebsiteEdit.fxml"));
        Scene scene = new Scene(WebsiteEditFxmlLoader.load(), 760, 500);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}
