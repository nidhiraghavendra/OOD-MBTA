/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
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
    @FXML
    private ImageView imageview1;

    @FXML
    private ImageView imageviewride1;
    
    private ApplicationSystem app;

    @FXML
    private Label welcomeUserField;
    @FXML
    private Label welcomeUserField1;

    @FXML
    private Label charliestatus;

    @FXML
    private Label ridepassstatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.app = ApplicationSystem.getInstance();
        UserAccount useraccount = this.app.getLoggedInUserAccount();
        if (useraccount != null) {
            displayCard(useraccount);
        }
    }

    private void displayCard(UserAccount useraccount) {
        
        useraccount.getCharlieCard().calculateCardBalance();
        useraccount.getRidePass().calculateCardBalance();
        charliestatus.setText(useraccount.getCharlieCard().getCardStatus());
        ridepassstatus.setText(useraccount.getRidePass().getCardStatus());
        welcomeUserField.setText(useraccount.getName());
        welcomeUserField1.setText(useraccount.getName());
        Image image = new Image(useraccount.getCharlieCard().getQRCodePath());
        imageview1.setImage(image);

        Image image2 = new Image(useraccount.getRidePass().getQRCodePath());
        imageviewride1.setImage(image2);
    }

}
