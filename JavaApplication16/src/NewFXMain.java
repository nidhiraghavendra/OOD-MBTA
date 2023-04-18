

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Nidhi Raghavendra
 */
public class NewFXMain extends Application {

    @Override
    public void start(Stage primaryStage)  {

        try {
            FXMLLoader loader = new FXMLLoader();
            System.out.println(3);
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/MainFXML.fxml"));
            Scene scene = new Scene(root, 1000, 1000);

            primaryStage.setTitle("MBTA Portal");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}