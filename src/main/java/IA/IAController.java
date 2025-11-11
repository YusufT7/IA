package IA;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    TextField txtBrand = new TextField();
    @FXML
    ComboBox cmbType = new ComboBox();
    @FXML
    ColorPicker cmbPrimColor = new ColorPicker();
    @FXML
    ColorPicker cmbSecColor = new ColorPicker();
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
    Button btnBack = new Button();
    @FXML
    CheckBox chkPenalty = new CheckBox();
    @FXML
    CheckBox chkPlate = new CheckBox();
    @FXML
    Label labelPhoto = new Label();
    @FXML
    Label labelRegistration = new Label();
    @FXML
    Label labelRegPhoto = new Label();
    @FXML
    Label labelInsPhoto = new Label();
    Connection conn;

    public IAController() throws SQLException {
        conn = MyConnection.connectDB();
    }

    public void initialize() {
        String[] types = {"Bicycle", "Scooter", "E-Scooter", "E-Bike"};
        String[] purpose = {"Private/Personal use", "Commercial use", "Government use", "Temporary registration"};
        //this is done so that the label shows multiple lines instead of keeping it as one
        labelRegistration.setText("(Any documents proof the ownership of the " +
                "\n motorized vehicle (e.g. government " +
                "\n registration, purchase order, sale " +
                "\n invoices, email agreement, etc...))");
        //initializes values of comboBox of motorized brand
        //intelliJ
        cmbType.getItems().addAll(types);
        cmbPurpose.getItems().addAll(purpose);
        chkPlate.setText("I acknowledge that I will return my motorized plate to security when I leave KAUST or whenever am no longer using the " +
                "\n assigned motorized vehicle plate. And will change the assigned plate number to any other motorized without security " +
                "\n approval and will report it to security");

    }

    public void onClickRegister(ActionEvent actionEvent) throws SQLException, IOException {
        //gets all input values and assigns them to variables
        String first_name = txtFName.getText();
        System.out.println(first_name);
        String last_name = txtLName.getText();
        String userId = txtID.getText();
        int numUserId = Integer.parseInt(txtID.getText());
        String phone_num = txtMobile.getText();
        String serialNum = txtSerialNum.getText();
        String brand = txtBrand.getText();
        String vehicleType = (String) cmbType.getValue();
        String primColor = String.valueOf(cmbPrimColor.getValue());
        String secColor = String.valueOf(cmbSecColor.getValue());
        String purpose = (String) cmbPurpose.getValue();
        String vehiclePhoto = labelPhoto.getText();
        String registrationPhoto = labelRegPhoto.getText();
        String insurancePhoto = labelInsPhoto.getText();
        String logged_in_email = User.getCurrentUser().getEmail(); //gets email of user with current email being used


        //checks if any of the inputs (text field, combobox, etc.) is empty
        if (first_name.isEmpty() || last_name.isEmpty() || phone_num.isEmpty() || serialNum.isEmpty()
                || userId.isEmpty() || brand.isEmpty() || vehicleType.isEmpty()
                || primColor.isEmpty() || secColor.isEmpty()
                || vehiclePhoto.equals("No selected photo/file")
                || registrationPhoto.equals("No selected photo/file")
                || insurancePhoto.equals("No selected photo/file")
                || chkSafety == null
                || chkPenalty == null || chkPlate.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Did not fill all required fields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all missing fields");
            alert.showAndWait();
        } else {
            //instead of making a new object every time, use.getInstance() so that all data stays in the same
            Registered registered = Registered.getInstance();
            //updates info to database (of logged-in user)
            //users
            String query1 = "UPDATE `users` SET `first_name`=?,`last_name`=?,`phone_num`=?,`user_id`=? WHERE email = ?";
            PreparedStatement ps = conn.prepareStatement(query1);
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setInt(3, Integer.parseInt(phone_num));
            ps.setInt(4, numUserId);
            //ensures that the data being updated is happening in the correct column (of which is the logged-in user)
            ps.setString(5, logged_in_email);
            int rowsAffected = ps.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated (in user database).");
            registered.addUser(User.getCurrentUser()); //might need to fix or exclude
            //adds new row of vehicle based on user input
            //vehicles
            String query2 = "INSERT INTO vehicles(primary_color, type, serial_number, status, brand, secondary_color, user_id, purpose) VALUES (?, ?, ?, 'new', ?, ?, ?, ?)";
            PreparedStatement ps2 = conn.prepareStatement(query2);
            ps2.setString(1, primColor);
            ps2.setString(2, vehicleType);
            ps2.setString(3, serialNum);
            ps2.setString(4, brand);
            ps2.setString(5, secColor);
            ps2.setInt(6, numUserId);
            ps2.setString(7, purpose);
            int rowsAdded = ps2.executeUpdate();
            System.out.println(rowsAdded + " row(s) inserted (in vehicle database).");

            //add registered vehicle into arraylist of vehicles
            registered.addVehicle(new Vehicle("new", primColor, secColor, brand, vehicleType, serialNum),
                    Integer.parseInt(userId));

            //transfers to next fxml (homepage)
            FXMLLoader WebsiteHomeFxmlLoader = new FXMLLoader(IAApplication.class.getResource("WebsiteHome.fxml"));
            Scene scene = new Scene(WebsiteHomeFxmlLoader.load(), 600, 400);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();;
            stage.setScene(scene);
            stage.show();
        }

    }


    // code here for choosing photo in IA.fxml (when registering vehicle)
    public void onClickVehiclePhoto(ActionEvent actionEvent) throws SQLException {
        // source: stackoverflow
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo File");
        File photo = fileChooser.showOpenDialog(null);
        if (photo != null) {
            //g
            String name = photo.getName();
            //reduce length of file/photo name
            if (photo.getName().length() > 20) {
                name = photo.getName().substring(0, 20);
            }
            labelPhoto.setText("Selected photo: " + name + "...");
        }
    }

    //same as onClickVehiclePhoto()
    public void onClickRegistrationPhoto(ActionEvent actionEvent) throws SQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo File");
        File photo = fileChooser.showOpenDialog(null);
        if (photo != null) {
            String name = photo.getName();
            if (photo.getName().length() > 20) {
                name = photo.getName().substring(0, 20);
            }
            labelRegPhoto.setText("Selected photo: " + name + "...");
        }
    }

    //same as onClickVehiclePhoto()
    public void onClickInsurancePhoto(ActionEvent actionEvent) throws SQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Photo File");
        File photo = fileChooser.showOpenDialog(null);
        if (photo != null) {
            String name = photo.getName();
            if (photo.getName().length() > 20) {
                name = photo.getName().substring(0, 20);
            }
            labelInsPhoto.setText("Selected photo: " + name + "...");
        }
    }
    public void onClickBack(ActionEvent actionEvent) throws IOException {
        //go back to homepage without adding/registering new vehicle
        FXMLLoader WebsiteHomeFxmlLoader = new FXMLLoader(IAApplication.class.getResource("WebsiteHome.fxml"));
        Scene scene = new Scene(WebsiteHomeFxmlLoader.load(), 600, 400);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();;
        stage.setScene(scene);
        stage.show();
    }


}

