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
    @FXML
    Button btnSignUp = new Button();
    Connection conn = MyConnection.connectDB();

    public LogInController()throws SQLException {
        conn = MyConnection.connectDB();
    }

    public void onClickLogIn(ActionEvent actionEvent) throws IOException, SQLException {
        //compare email in fxml with email in database to see if account exists/user already registered
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
            String searchPassword = "SELECT * FROM users WHERE password = '" + txtPassword.getText() + "'";
            PreparedStatement ps4 = conn.prepareStatement(searchPassword);
            ResultSet rs4 = ps4.executeQuery(searchPassword);
            if(!rs4.next()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Email or Password Incorrect");
                alert.setHeaderText(null);
                alert.setContentText("Please re-enter the correct email and password");
                alert.showAndWait();
            }
            else{
                //code for checking if email is associated with government

                //loads website home fxml
                FXMLLoader WebsiteHomeFxmlLoader = new FXMLLoader(IAApplication.class.getResource("WebsiteHome.fxml"));
                Scene scene = new Scene(WebsiteHomeFxmlLoader.load(), 600, 400);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }

        }
    }
    public void onClickSignUp(ActionEvent actionEvent) throws IOException, SQLException {
        FXMLLoader SignUpFxmlLoader = new FXMLLoader(IAApplication.class.getResource("SignUp.fxml"));
        Scene scene = new Scene(SignUpFxmlLoader.load(), 435, 400);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
