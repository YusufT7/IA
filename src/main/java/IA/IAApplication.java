package IA;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IAApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(IAApplication.class.getResource("LogIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 435, 400);
        primaryStage.setTitle(null);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
