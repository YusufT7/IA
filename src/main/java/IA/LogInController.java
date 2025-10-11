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

public class LogInController {
    @FXML
    TextField txtEmail = new TextField();
    @FXML
    TextField txtPassword = new TextField();
    @FXML
    Button btnLogIn = new Button();
    Connection conn = MyConnection.connectDB();

    public LogInController()throws SQLException {
        conn = MyConnection.connectDB();
    }

    public void onClickLogIn(ActionEvent actionEvent) throws IOException, SQLException {
        String searchQuery = "SELECT * FROM users WHERE email = '" + txtEmail.getText() + "'";
        PreparedStatement ps3 = conn.prepareStatement(searchQuery);
        ResultSet rs3 = ps3.executeQuery(searchQuery);
        System.out.println(rs3);
        System.out.println(rs3);
        if(!rs3.next()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Email or Password Incorrect");
            alert.setHeaderText(null);
            alert.setContentText("Please re-enter the correct email and password");
            alert.showAndWait();
        }
        else{
            //compare password with db password
            
            FXMLLoader IAFxmlLoader = new FXMLLoader(IAApplication.class.getResource("WebsiteHome.fxml"));
            Scene scene = new Scene(IAFxmlLoader.load(), 600, 400);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

}
