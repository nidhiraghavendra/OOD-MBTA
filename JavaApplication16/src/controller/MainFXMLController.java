/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import javafx.scene.control.TextInputDialog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import model.Customer.Customer;
import model.Transaction.Transaction;
import model.UserAccount.UserAccount;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import javafx.scene.control.TextArea;

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
    private Label welcomeUserField1;
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

    @FXML
    private Button userBtn;

    @FXML
    private Button ride;

    @FXML
    private Button profileBtn;

    @FXML
    private Label charliestatus;

    @FXML
    private Label ridepassstatus;

    @FXML
    private TextField charlieAmt;

    @FXML
    private TextField rideAmt;

    @FXML
    private Button charlieBtn;
    @FXML
    private Button rideBtn;
    @FXML
    private Button charlie;
    @FXML
    private Button announcebtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        app = ApplicationSystem.getInstance();
        System.out.println("ONCE -- ");
        if (app.isUserLoggedIn()) {
            System.out.println("Present -- ");
            // use the user account object to show the welcome message
            UserAccount useraccount = app.getLoggedInUserAccount();

            welcomeUserField.setText(app.getLoggedInUserAccount().getName());
            welcomeUserField1.setText(app.getLoggedInUserAccount().getName());
            accountBtn.setVisible(false);
            profileBtn.setVisible(true);
            logoutBtn.setVisible(true);
//            anncebtn.setVisible(false);

            if (useraccount.getRole().getRoleType().equals("admin") || useraccount.getRole().getRoleType().equals("mbta")) {
                userBtn.setVisible(true);
                profileBtn.setVisible(false);
                borderpane.getRight().setVisible(false);
                announcebtn.setVisible(true);
         
            } else {
                userBtn.setVisible(false);
                profileBtn.setVisible(true);
                // SHOW QR CODE
                displayCard(useraccount);
                borderpane.getRight().setVisible(true);
            }

        } else {
            welcomeUserField.setText("");
            welcomeUserField1.setText("");
            accountBtn.setVisible(true);
            announcebtn.setVisible(false);
            profileBtn.setVisible(false);
            logoutBtn.setVisible(false);
            userBtn.setVisible(false);
            borderpane.getRight().setStyle("-fx-background-color: #ffff;");
            borderpane.getRight().setVisible(false);
        }	

    }

    @FXML
    private void GoToLoginAccount(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./resources/LoginFXML.fxml"));
        root = loader.load();
        ((LoginFXMLController) loader.getController()).setModel(this.app);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void GoToLogoutAccount(ActionEvent event) throws IOException {
        app.setUserLoggedIn(false);
        app.setLoggedInUserAccount(null);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./view/MainFXML.fxml"));
        root = loader.load();
        //((MainFXMLController)loader.getController()).setModel(this.app);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1000, 1000);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void trainButtonClicked(ActionEvent event) {

    }

    @FXML
    private void busButtonClicked(ActionEvent event) throws IOException {
    	System.out.println("hello");
        Pane loadPane = FXMLLoader.load(getClass().getClassLoader().getResource("./view/SearchRoutesFXML.fxml"));
        borderpane.setCenter(loadPane);
    }

    @FXML
    private void rideButtonClicked(ActionEvent event) throws IOException {
        Pane loadPane = FXMLLoader.load(getClass().getClassLoader().getResource("./view/RideFXML.fxml"));
        borderpane.setCenter(loadPane);
    }

    @FXML
    private void charlieButtonClicked(ActionEvent event) throws IOException {
        Pane loadPane = FXMLLoader.load(getClass().getClassLoader().getResource("./view/CharliePassFXML.fxml"));
        borderpane.setCenter(loadPane);
    }

    private void displayCard(UserAccount useraccount) {
        System.out.print(useraccount.getCharlieCard().getQRCodePath());
        Image image = new Image(useraccount.getCharlieCard().getQRCodePath());
        imageview.setImage(image);
        imageview.setStyle("-fx-stroke-width: 2; -fx-stroke: blue");

        Image image2 = new Image(useraccount.getRidePass().getQRCodePath());
        imageviewride.setImage(image2);
        imageviewride.setStyle("-fx-stroke-width: 2; -fx-stroke: blue");

        useraccount.getCharlieCard().calculateCardBalance();
        useraccount.getRidePass().calculateCardBalance();
        charliestatus.setText(useraccount.getCharlieCard().getCardStatus());
        ridepassstatus.setText(useraccount.getRidePass().getCardStatus());
    }

    @FXML
    private void userBtnClicked(ActionEvent event) {
        try {
            // TODO

            Pane loadPane = FXMLLoader.load(getClass().getClassLoader().getResource("./view/UserManagementFXML.fxml"));
            borderpane.setCenter(loadPane);

        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void profileBtnClicked(ActionEvent event) {
        try {
            // TODO

            Pane loadPane = FXMLLoader.load(getClass().getClassLoader().getResource("./view/UserProfileFXML.fxml"));
            borderpane.setCenter(loadPane);

        } catch (IOException ex) {
            Logger.getLogger(MainFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void charlieBtnClicked(ActionEvent event) {
        UserAccount user = this.app.getLoggedInUserAccount();
        Customer customer = this.app.getCustomerDirectory().findACustomer(user);
        if (charlieAmt.getText().equals("")) {
            user.getCharlieCard().calculateCardBalance();
            boolean result = user.getCharlieCard().deductAmount(0.0);
            if (result) {

                Transaction t = customer.addTransaction();
                t.setAmount(user.getCharlieCard().passFee);
                t.setStatus("Paid");
                t.setTransactionType("charlie card");
            } else {
                Transaction t = customer.addTransaction();
                t.setAmount(user.getCharlieCard().passFee);
                t.setStatus("Failed");
                t.setTransactionType("charlie card");
            }
        } else {
            boolean result = user.getCharlieCard().deductAmount(Double.valueOf(charlieAmt.getText()));
            if (result) {

                Transaction t = customer.addTransaction();
                t.setAmount(Double.valueOf(charlieAmt.getText()));
                t.setStatus("Paid");
                t.setTransactionType("charlie card");
            } else {
                Transaction t = customer.addTransaction();
                t.setAmount(Double.valueOf(charlieAmt.getText()));
                t.setStatus("Failed");
                t.setTransactionType("charlie card");
            }
        }
        user.getCharlieCard().calculateCardBalance();
        charliestatus.setText(user.getCharlieCard().getCardStatus());
    }

    @FXML
    private void rideBtnClicked(ActionEvent event) {
        UserAccount user = this.app.getLoggedInUserAccount();
        Customer customer = this.app.getCustomerDirectory().findACustomer(user);
        if (rideAmt.getText().equals("")) {
            user.getRidePass().calculateCardBalance();
            boolean result = user.getRidePass().deductAmount(0.0);
            if (result) {
                Transaction t = customer.addTransaction();
                t.setAmount(user.getRidePass().passFee);
                t.setStatus("Paid");
                t.setTransactionType("ride pass");
            } else {
                Transaction t = customer.addTransaction();
                t.setAmount(user.getRidePass().passFee);
                t.setStatus("Failed");
                t.setTransactionType("ride pass");
            }
        } else {
            boolean result = user.getRidePass().deductAmount(Double.valueOf(rideAmt.getText()));
            if (result) {
                Transaction t = customer.addTransaction();
                t.setAmount(Double.valueOf(rideAmt.getText()));
                t.setStatus("Paid");
                t.setTransactionType("ride pass");
            } else {
                Transaction t = customer.addTransaction();
                t.setAmount(Double.valueOf(rideAmt.getText()));
                t.setStatus("Failed");
                t.setTransactionType("ride pass");
            }
        }
        user.getRidePass().calculateCardBalance();	
        ridepassstatus.setText(user.getRidePass().getCardStatus());
    }
    @FXML
    private void announcebtnClicked(ActionEvent event) throws IOException 
    {
    	
    	System.out.println("hello");
    	TextArea textArea = new TextArea();
    	
    	// Set text
    	textArea.setText("Hello");
        TextInputDialog td = new TextInputDialog("enter any text");
        td.setX(100);
        td.setHeaderText("Enter the announcement");
        Pane loadPane = FXMLLoader.load(getClass().getClassLoader().getResource("./resources/Announcements.fxml"));
        borderpane.setCenter(loadPane);
        
//        Optional<String> result = td.showAndWait();
//       
//        System.out.println(result.toString());
    	
    }

}
