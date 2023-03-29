/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.MouseEvent;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.ApplicationSystem.ApplicationSystem;
import model.Role.CustomerRole;
import model.UserAccount.UserAccount;
import model.UserAccount.UserAccountDirectory;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author Nidhi Raghavendra
 */
public class LoginFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Parent root;
    Stage stage;
    ApplicationSystem app;

    @FXML
    private TextField fieldUsername;

    @FXML
    private TextField fieldPassword;

    @FXML
    private TextField fieldNewUsername;

    @FXML
    private TextField fieldNewPassword;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldEmail;

    @FXML
    private Button buttonLogin;
    
    
    @FXML
    private Button buttonSignUp;

    public void setModel(ApplicationSystem model) {
        this.app = model;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }

    @FXML
    private void backButtonAction(ActionEvent event) throws IOException {
        System.out.print(" CAME HERE ");
        root = FXMLLoader.load(getClass().getClassLoader().getResource("./view/MainFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonSignUpClicked(ActionEvent event) {
        try {
            String usenrame = fieldNewUsername.getText();
            String pass = fieldNewPassword.getText();
            Boolean user = app.getUserdirectory().checkIfExists(usenrame, pass);
            if (user) {
                // show a message dialogue

                System.out.println("Already in");

            } else {

                UserAccount useracc = app.getUserdirectory().createNewUserAccount(fieldNewPassword.getText(), fieldNewPassword.getText(), fieldName.getText(), fieldEmail.getText());
                useracc.setRole(new CustomerRole());
                String QRCodeText = useracc.getUsername() + useracc.getUseraccountId() + "MBTA charlie card";
                String QRCodeTextRide = useracc.getUsername() + useracc.getUseraccountId() + "MBTA ride pass";
//                Generate a new Charlie card and Ride Pass for the user
                ByteArrayInputStream in = getQRCode(QRCodeText);
                useracc.getCharlieCard().setQRCodePath(in);
                
                ByteArrayInputStream inRide = getQRCode(QRCodeTextRide);
                useracc.getRidePass().setQRCodePath(inRide);
                
                app.getCustomerDirectory().createCustomer(useracc);
                
                buttonSignUp.setText("Registered");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buttonLoginClicked(ActionEvent event) throws IOException {

        boolean present = app.getUserdirectory().checkIfExists(fieldUsername.getText(), fieldPassword.getText());
        if (present) {
            UserAccount user = app.getUserdirectory().findUser(fieldUsername.getText(), fieldPassword.getText());
            app.setUserLoggedIn(true);
            app.setLoggedInUserAccount(user);

            root = FXMLLoader.load(getClass().getClassLoader().getResource("./view/MainFXML.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();;
            Scene scene = new Scene(root, 1000, 1000);
            stage.setScene(scene);
            stage.show();
        }

    }

    private ByteArrayInputStream getQRCode(String QRCodeText) {
        ByteArrayOutputStream out = QRCode.from(QRCodeText).to(ImageType.PNG).withSize(100, 100).stream();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        
        return in;
    }
}
