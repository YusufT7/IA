package IA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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
    TextField txtID = new TextField();
    @FXML
    ComboBox cmbType = new ComboBox();
    @FXML
    ColorPicker cmbPrimColor = new ColorPicker();
    @FXML
    ColorPicker cmbSecColor = new ColorPicker();
    @FXML
    ComboBox cmbBrand = new ComboBox();
    @FXML
    ComboBox cmbPurpose = new ComboBox();
    @FXML
    ToggleButton tglButton = new ToggleButton();
    @FXML
    CheckBox chkSafety = new CheckBox();
    @FXML
    Button btnRegistration = new Button();
    @FXML
    Button btnPhoto = new Button();
    @FXML
    Button btnInsurance = new Button();
    @FXML
    Button btnRegister = new Button();
    @FXML
    CheckBox chkPenalty = new CheckBox();
    @FXML
    CheckBox chkPlate = new CheckBox();
    @FXML
    Label labelNum = new Label();
    @FXML
    Label labelPhoto = new Label();
    @FXML
    Label labelRegistration = new Label();
    @FXML
    Label labelRegPhoto = new Label();
    Connection conn;

    public IAController() throws SQLException{
        conn = MyConnection.connectDB();
    }

    public void initializeStickerNum(){
        // add code here where the label for sticker number is randomly generated
        // make sure the sticker number is stored in database(?)
    }
    public void initialize(){
        //this is done so that the label shows multiple lines instead of keeping it as one
        labelRegistration.setText("(Any documents proof the ownership of the " +
                "\n motorized vehicle (e.g. government " +
                "\n registration, purchase order, sale " +
                "\n invoices, email agreement, etc...))");
    }

    public void onClickRegister(ActionEvent actionEvent) throws SQLException, IOException {
        //gets all input values and assigns them to variables
        String first_name = txtFName.getText();
        String last_name = txtLName.getText();
        int userId = Integer.parseInt(txtID.getText());
        String phone_num = txtMobile.getText();
        String serialNum = txtSerialNum.getText();
        String vehicleType = (String) cmbType.getValue();
        String brand = (String) cmbBrand.getValue();
        String primColor = String.valueOf(cmbPrimColor.getValue());
        String secColor = String.valueOf(cmbSecColor.getValue());

        //adds info to database
        //users
        String query1 = "SELECT * FROM users WHERE first_name=? AND last_name=? AND phone_num=? AND user_id=?";
        PreparedStatement ps = conn.prepareStatement(query1);
        ps.setString(1, first_name);
        ps.setString(2, last_name);
        ps.setString(3, phone_num);
        ps.setString(4, String.valueOf(userId));

        //vehicles
        String query2 = "SELECT * FROM vehicles WHERE 1 primColor=? AND vehicleType=? AND serialNum=? AND status=? AND brand=? AND secColor=?";
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

        //code here to transfer to next fxml ui (WebsiteHome.fxml)
        FXMLLoader fxmlLoader2 = new FXMLLoader(IAApplication.class.getResource("WebsiteHome.fxml"));
        Scene scene = new Scene(fxmlLoader2.load(), 600, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    // code here for choosing photo in IA.fxml (when registering vehicle)
    // source: stackoverflow
    public void onClickVehiclePhoto(ActionEvent actionEvent) throws SQLException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo File");
        File photo = fileChooser.showOpenDialog(null);
        if (photo != null) {
            //code here
            labelPhoto.setText("Selected photo: " + photo.getName());
        }
    }

    //same as onClickVehiclePhoto()
    public void onClickRegistrationPhoto(ActionEvent actionEvent) throws SQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo File");
        File photo = fileChooser.showOpenDialog(null);
        if (photo != null) {
            labelRegPhoto.setText("Selected photo: " + photo.getName());
        }
    }

}

