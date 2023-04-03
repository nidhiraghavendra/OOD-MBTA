/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import model.ApplicationSystem.ApplicationSystem;

/**
 * FXML Controller class
 *
 * @author Nidhi Raghavendra
 */
public class UserProfileFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    ApplicationSystem app;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.app = ApplicationSystem.getInstance();
    }    
    
}
