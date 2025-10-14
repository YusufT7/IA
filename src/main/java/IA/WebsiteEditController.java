package IA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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


public class WebsiteEditController {
    @FXML
    TextField txtEditSerialNum = new TextField();
    @FXML
    TextField txtEditBrand = new TextField();
    @FXML
    TextField txtOldSerialNum = new TextField();
    @FXML
    ComboBox cmbEditType = new ComboBox();
    @FXML
    ComboBox cmbEditPurpose = new ComboBox();
    @FXML
    ColorPicker cmbEditPrimary = new ColorPicker();
    @FXML
    ColorPicker cmbEditSecondary = new ColorPicker();
    @FXML
    Button btnReselectVPhoto = new Button();
    @FXML
    Button btnReselectRPhoto = new Button();
    @FXML
    Button btnReselectPolicy = new Button();
    @FXML
    Button btnSubmitEdit = new Button();
    @FXML
    Button btnBack = new Button();
    @FXML
    Label lblReg = new Label();
    @FXML
    Label lblVPhoto = new Label();
    @FXML
    Label lblRPhoto = new Label();
    @FXML
    Label lblPolicy = new Label();
    Connection conn;

    public WebsiteEditController() throws SQLException {
        conn = MyConnection.connectDB();
    }
    public void initialize() {
        String[] types = {"Bicycle", "Scooter", "E-Scooter", "E-Bike"};
        String[] purpose = {"Private/Personal use", "Commercial use", "Government use", "Temporary registration"};
        //this is done so that the label shows multiple lines instead of keeping it as one
        lblReg.setText("(Any documents proof the ownership of the " +
                "\n motorized vehicle (e.g. government " +
                "\n registration, purchase order, sale " +
                "\n invoices, email agreement, etc...))");
        //initializes values of comboBox of motorized brand
        //source: intelliJ
        cmbEditType.getItems().addAll(types);
        cmbEditPurpose.getItems().addAll(purpose);

    }
    public void onClickBack(ActionEvent actionEvent) throws IOException {
        FXMLLoader WebsiteHomeFxmlLoader = new FXMLLoader(IAApplication.class.getResource("WebsiteHome.fxml"));
        Scene scene = new Scene(WebsiteHomeFxmlLoader.load(), 600, 400);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    //same methods in IAController
    public void onClickNewVehiclePhoto(ActionEvent actionEvent) throws SQLException {
        // source: stackoverflow
        FileChooser newFileChooser = new FileChooser();
        newFileChooser.setTitle("Select Photo File");
        File photo = newFileChooser.showOpenDialog(null);
        if (photo != null) {
            //g
            String name = photo.getName();
            //reduce length of file/photo name
            if (photo.getName().length() > 20) {
                name = photo.getName().substring(0, 20);
            }
            lblVPhoto.setText("Selected photo: " + name + "...");
        }
    }
    public void onClickNewRegistrationPhoto(ActionEvent actionEvent) throws SQLException {
        FileChooser newFileChooser = new FileChooser();
        newFileChooser.setTitle("Select Photo File");
        File photo = newFileChooser.showOpenDialog(null);
        if (photo != null) {
            String name = photo.getName();
            if (photo.getName().length() > 20) {
                name = photo.getName().substring(0, 20);
            }
            lblRPhoto.setText("Selected photo: " + name + "...");
        }
    }

    public void onClickNewInsurancePhoto(ActionEvent actionEvent) throws SQLException {
        FileChooser newFileChooser = new FileChooser();
        newFileChooser.setTitle("Select Photo File");
        File photo = newFileChooser.showOpenDialog(null);
        if (photo != null) {
            String name = photo.getName();
            if (photo.getName().length() > 20) {
                name = photo.getName().substring(0, 20);
            }
            lblPolicy.setText("Selected photo: " + name + "...");
        }
    }
    public void onClickSubmitEdit(ActionEvent actionEvent) throws SQLException, IOException {
        String primaryColor = String.valueOf(cmbEditPrimary.getValue());
        String secondaryColor = String.valueOf(cmbEditSecondary.getValue());
        String vehicleType = String.valueOf(cmbEditType.getValue());
        String vehiclePurpose = String.valueOf(cmbEditPurpose.getValue());
        String newSerialNum = txtEditSerialNum.getText();
        String vehicleBrand = txtEditBrand.getText();
        String oldSerialNum = txtOldSerialNum.getText();
        if(vehicleBrand.isEmpty() || newSerialNum.isEmpty() || vehicleType.isEmpty() || vehiclePurpose.isEmpty()
        || primaryColor.isEmpty() || secondaryColor.isEmpty() || oldSerialNum.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Did not fill all required fields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all missing fields");
            alert.showAndWait();
        }
        else{
            String userEmail = User.getCurrentUser().getEmail();
            int userId = User.getUserIdDB(conn, userEmail);
            String updatedVQuery = "UPDATE vehicles SET primary_color=?, type=?, serial_number=?, status = 'Updated', brand=?, secondary_color=?, purpose=? WHERE user_Id = ? AND serial_number = ?";
            PreparedStatement ps = conn.prepareStatement(updatedVQuery);
            ps.setString(1, primaryColor);
            ps.setString(2, vehicleType);
            ps.setString(3, newSerialNum);
            ps.setString(4, vehicleBrand);
            ps.setString(5, secondaryColor);
            ps.setString(6, vehiclePurpose);
            ps.setInt(7, userId);
            ps.setString(8, oldSerialNum);
            int rowsAffected = ps.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated (in user database).");

            FXMLLoader WebsiteHomeFxmlLoader = new FXMLLoader(IAApplication.class.getResource("WebsiteHome.fxml"));
            Scene scene = new Scene(WebsiteHomeFxmlLoader.load(), 600, 400);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();;
            stage.setScene(scene);
            stage.show();
        }

    }

}
