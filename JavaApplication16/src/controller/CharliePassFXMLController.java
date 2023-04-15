/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.ApplicationSystem.ApplicationSystem;
import model.UserAccount.UserAccount;

/**
 * FXML Controller class
 *
 * @author Nidhi Raghavendra
 */
public class CharliePassFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private ApplicationSystem app;

    @FXML
    private Label welcomeUserField;
    @FXML
    private Label welcomeUserField1;

    @FXML
    private Label charliestatus;
    
    @FXML
    private ImageView view1;
    @FXML
    private ImageView view2;
    
    @FXML
    private Label ridepassstatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.app = ApplicationSystem.getInstance();
        UserAccount useraccount = this.app.getLoggedInUserAccount();
        if (this.app.isUserLoggedIn()) {
            displayCard(useraccount);
        }
    }

    public void displayCard(UserAccount useraccount) {
        
        useraccount.getCharlieCard().calculateCardBalance();
        useraccount.getRidePass().calculateCardBalance();
        charliestatus.setText(useraccount.getCharlieCard().getCardStatus());
        ridepassstatus.setText(useraccount.getRidePass().getCardStatus());
        welcomeUserField.setText(useraccount.getName());
        welcomeUserField1.setText(useraccount.getName());
        
        System.out.print(useraccount.getCharlieCard().getQRCodePath());
                
        Image image = new Image("s.jpg");
        view1.setImage(image);
        view1.setCache(false);
        view1.setStyle("-fx-stroke-width: 2; -fx-stroke: blue");

        Image image2 = new Image("s.jpg");
        view2.setImage(image2);
        view2.setStyle("-fx-stroke-width: 2; -fx-stroke: blue");
        
    }

}
