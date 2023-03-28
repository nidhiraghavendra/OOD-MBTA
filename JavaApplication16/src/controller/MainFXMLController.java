/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.annotation.processing.Generated;
import model.ApplicationSystem.ApplicationSystem;
import model.UserAccount.UserAccount;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author Nidhi Raghavendra
 */
public class MainFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    Parent root;
    Stage stage;
    private ApplicationSystem app;
    
    @FXML
    private Label welcomeUserField;
    
    @FXML
    private Button accountBtn;
    
    @FXML
    private Button logoutBtn;
    
    @FXML 
    private BorderPane borderpane;
    
    @FXML
    private ImageView imageview;
    
    @FXML
    private ImageView imageviewride;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        app = ApplicationSystem.getInstance();
        System.out.println("ONCE -- ");
        if(app.isUserLoggedIn()) {
            System.out.println("Present -- ");
            // use the user account object to show the welcome message
            UserAccount useraccount = app.getLoggedInUserAccount();
            
            welcomeUserField.setText(app.getLoggedInUserAccount().getName());
            accountBtn.setVisible(false);
            logoutBtn.setVisible(true);
            
            // SHOW QR CODE
            displayCard(useraccount);
            borderpane.getRight().setVisible(true);
        
        
        } else {
            welcomeUserField.setText("");
            accountBtn.setVisible(true);
            logoutBtn.setVisible(false);
            borderpane.getRight().setVisible(false);
        }
        
        
    }    

    @FXML
    private void GoToLoginAccount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./resources/LoginFXML.fxml")) ;   
        root = loader.load();
        ((LoginFXMLController)loader.getController()).setModel(this.app);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        stage.show();
        
    }
    
    @FXML
    private void GoToLogoutAccount(ActionEvent event) throws IOException {
        app.setUserLoggedIn(false);
        app.setLoggedInUserAccount(null);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./view/MainFXML.fxml")) ;   
        root = loader.load();
        //((MainFXMLController)loader.getController()).setModel(this.app);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        stage.show();
    }
 
    @FXML
    private void trainButtonClicked(ActionEvent event) {
        
    }
    
    @FXML
    private void busButtonClicked(ActionEvent event) {
        
    }
    
    @FXML
    private void rideButtonClicked(ActionEvent event) {
        
    }
    
    @FXML
    private void charlieButtonClicked(ActionEvent event) {
        
    }
    
    private void displayCard(UserAccount useraccount) {
        Image image = new Image(useraccount.getCharlieCard().getQRCodePath());
        imageview.setImage(image);
        imageview.setStyle("-fx-stroke-width: 2; -fx-stroke: blue");
       
        Image image2 = new Image(useraccount.getRidePass().getQRCodePath());
        imageviewride.setImage(image2);
        imageviewride.setStyle("-fx-stroke-width: 2; -fx-stroke: blue");
    }
}
