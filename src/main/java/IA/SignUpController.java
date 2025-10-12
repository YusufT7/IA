package IA;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpController {
    @FXML
    TextField txtNewEmail = new TextField();
    @FXML
    TextField txtNewPassword = new TextField();
    @FXML
    TextField txtNewPasswordConfirm = new TextField();
    @FXML
    Button btnCreateAccount = new Button();
    @FXML
    Button btnLogIn = new Button();
    Connection conn;

    public SignUpController() throws SQLException {
        conn = MyConnection.connectDB();
    }

    public void onClickCreateAccount(ActionEvent actionEvent) throws SQLException, IOException {
        if(txtNewEmail.getText().isEmpty() || txtNewPassword.getText().isEmpty() || txtNewPasswordConfirm.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Did not fill all required fields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all missing fields");
            alert.showAndWait();
        }
        else{
            //checks if email actually exists or is appropriate
            if(EmailVerifier.isEmailValid(txtNewEmail.getText())){
                //checks if password is 8 characters long
                if(txtNewPassword.getText().length()>= 8){
                    //checks if verified password is exactly the same as created password
                    if(txtNewPasswordConfirm.getText().equals(txtNewPassword.getText())){
                        //code for adding a new row of user to database that contains new email and password
                        String newQuery = "INSERT INTO users (first_name, last_name, phone_num, user_id, email, userType, password) VALUES ('', '', 0, 0, ?, 'User', ?)";
                        PreparedStatement ps = conn.prepareStatement(newQuery);
                        ps.setString(1, txtNewEmail.getText());
                        ps.setString(2, txtNewPassword.getText());
                        int rowsAffected = ps.executeUpdate();
                        System.out.println(rowsAffected + " row(s) inserted.");

                        //code for transferring to WebsiteHome.fxml
                        FXMLLoader WebsiteHomeFxmlLoader = new FXMLLoader(IAApplication.class.getResource("WebsiteHome.fxml"));
                        Scene scene = new Scene(WebsiteHomeFxmlLoader.load(), 600, 400);
                        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Verified password does not match with created password");
                        alert.setHeaderText(null);
                        alert.setContentText("Please re-enter correct verified password");
                        alert.showAndWait();
                    }
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Password is not 8 or more characters long");
                    alert.setHeaderText(null);
                    alert.setContentText("Please re-enter password that is at least 8 or more characters long");
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Email does not exist or 'is already used'");
                alert.setHeaderText(null);
                alert.setContentText("Please re-enter appropriate email");
                alert.showAndWait();
            }

        }

    }
    public void onClickLogIn(ActionEvent actionEvent) throws IOException {
        FXMLLoader LogInFxmlLoader = new FXMLLoader(IAApplication.class.getResource("LogIn.fxml"));
        Scene scene = new Scene(LogInFxmlLoader.load(), 435, 400);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
