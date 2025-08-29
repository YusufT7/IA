package IA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IAController {
    @FXML
    TextField txtFName = new TextField();
    @FXML
    TextField txtLName = new TextField();
    @FXML
    TextField txtMobile = new TextField();
    @FXML
    TextField txtSerialNum = new TextField();
    @FXML
    ComboBox cmbType = new ComboBox();
    @FXML
    ComboBox cmbColor = new ComboBox();
    @FXML
    ComboBox cmbBrand = new ComboBox();
    @FXML
    Label labelN = new Label();
    Connection conn;

    public IAController() throws SQLException{
        conn = MyConnection.connectDB();
    }
    public void OnClickRegister(ActionEvent actionEvent) throws SQLException{
        String first_name = txtFName.getText();
        String last_name = txtLName.getText();
        String mobile = txtMobile.getText();
        String serialNum = txtSerialNum.getText();
        String vehicleType = (String) cmbType.getValue();

        String query = "SELECT * FROM users WHERE user_product_id=? AND first_name=? AND last_name=? AND phone_num=? AND email=?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, first_name);
        ps.setString(2, last_name);
        ps.setString(3, mobile);
        ps.setString(4, serialNum);
        ps.setString(5, vehicleType);

        ResultSet rs = ps.executeQuery();

        if (rs.next()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("welcome");
            alert.setHeaderText(null);
            alert.setContentText("Login successful");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            txtFName.setText("");
            txtLName.setText("");
            txtMobile.setText("");
            txtSerialNum.setText("");
            cmbType.getItems().clear();
        }
    }

}
