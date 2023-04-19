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
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javax.annotation.processing.Generated;
import model.ApplicationSystem.ApplicationSystem;
import model.Customer.Customer;
import model.Transaction.Transaction;
import model.UserAccount.UserAccount;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import model.Announcement.Announcement;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import model.Card.CharlieCard;
import model.Card.RidePass;

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
    private Button charlieButton;
    @FXML
    private Button announcebtn;
    @FXML
    private Button faqBtn;
    @FXML
    private Button Stationsbtn;
    @FXML
    private Pane centerpane;
   
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
                Stationsbtn.setVisible(true);
                charlieButton.setVisible(false);
            } else {
                userBtn.setVisible(false);
                profileBtn.setVisible(true);
                announcebtn.setVisible(false);
                Stationsbtn.setVisible(false);
                charlieButton.setVisible(true);
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
            Stationsbtn.setVisible(false);
//            borderpane.getRight().setStyle("-fx-background-color: #ffff;");
            borderpane.getRight().setVisible(false);
            ListView<String> list = new ListView<>();
            list.setItems(getArrayList());
            list.setPrefWidth(500);
            list.setPrefHeight(500);
        }
        
        if (app.getAnnouncementslist().size() != 0) {
            VBox vbox = getAnnouncements();
            BackgroundImage bi = new BackgroundImage(new Image("train.jpg", 1200, 5000, true, true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
           
            centerpane.setBackground(new Background(bi));
            centerpane.getChildren().add(vbox);
//            centerpane.setStyle("-fx-background-size: cover;");
        }

    }

    @FXML
    private void GoToLoginAccount(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./resources/LoginFXML.fxml"));
//        root = loader.load();
////        ((LoginFXMLController) loader.getController()).setModel(this.app);
//        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        Scene scene = new Scene(root, 2500, 2500);
//        stage.setMaximized(true);
//        stage.setScene(scene);
//        stage.show();
        Pane loadPane = FXMLLoader.load(getClass().getClassLoader().getResource("./resources/LoginFXML.fxml"));
        borderpane.setCenter(loadPane);
        borderpane.getRight().setVisible(false);
        
        
    }

    @FXML
    private void GoToLogoutAccount(ActionEvent event) throws IOException {
        app.setUserLoggedIn(false);
        app.setLoggedInUserAccount(null);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./view/MainFXML.fxml"));
        root = loader.load();
        //((MainFXMLController)loader.getController()).setModel(this.app);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 2500, 2500);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
        

    }

    @FXML
    private void trainButtonClicked(ActionEvent event) throws IOException {
        Pane loadPane = FXMLLoader.load(getClass().getClassLoader().getResource("./view/SearchRoutesFXML.fxml"));
        borderpane.setCenter(loadPane);
       
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
        BackgroundImage bi = new BackgroundImage(new Image	("ridesimage.png", 2000, 2000, true, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT); 
        loadPane.setBackground(new Background(bi));

        borderpane.setCenter(loadPane);
        
    }

    @FXML
    private void charlieButtonClicked(ActionEvent event) throws IOException {
        Pane loadPane = FXMLLoader.load(getClass().getClassLoader().getResource("./view/CharliePassFXML.fxml"));
        borderpane.setCenter(loadPane);
        BackgroundImage bi = new BackgroundImage(new Image	("cardimage.png", 2000, 2000, true, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT); 
        loadPane.setBackground(new Background(bi));
//        borderpane.setBackground(new Background(bi));

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
    private void announcebtnClicked(ActionEvent event) throws IOException {

        System.out.println("hello");
        TextArea textArea = new TextArea();

        // Set text
        textArea.setText("Hello");
        TextInputDialog td = new TextInputDialog("enter any text");
        td.setX(100);
        td.setHeaderText("Enter the announcement");
        
        Pane loadPane = FXMLLoader.load(getClass().getClassLoader().getResource("./resources/Announcements.fxml"));
        borderpane.setCenter(loadPane);
        BackgroundImage bi = new BackgroundImage(new Image	("train-announcement.jpg", 2000, 2000, true, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT); 
        borderpane.setBackground(new Background(bi));
    }
    @FXML
    private void Stationsbtnclicked(ActionEvent event) throws IOException
    {
    	System.out.println("AAAAAAAAAAAA");
        Pane loadPane = FXMLLoader.load(getClass().getClassLoader().getResource("./view/Stations.fxml"));
        borderpane.setCenter(loadPane);
    }
    
    private ObservableList<String> getArrayList() {
        ObservableList<String> announcementlist = FXCollections.observableArrayList();
        app.getAnnouncementslist().forEach((i) -> {
            announcementlist.add(i.getDescription());
        });
        return announcementlist;
    }

    @FXML
    private void faqBtnClicked(ActionEvent e) {
        VBox vbox = getAnnouncements();
        borderpane.setCenter(getAnnouncements());
    }

    private VBox getAnnouncements() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(500, 500, 500, 500));
        vbox.setSpacing(25);
        vbox.setAlignment(Pos.CENTER);
        
        Label ro = new Label("ANNOUNCEMENTS");
        ro.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        ro.setStyle("-fx-background-color: aliceblue");
        vbox.getChildren().add(ro);
        vbox.setMaxHeight(500.0);
        
        for (int i = 0; i < getArrayList().size(); i++) {
            String val = getArrayList().get(i);
            HBox hbox = new HBox();
            Text label = new Text();
            label.setWrappingWidth(800);
            label.setText(i + 1 + ". " + val);
            label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            hbox.getChildren().add(label);
            hbox.setStyle("-fx-border-color: orange; -fx-background-color: #f9c784;" + "-fx-border-width: 2;" + "-fx-padding: 10;");
            hbox.setMargin(label, new Insets(10, 10, 10, 10));
            vbox.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
                    + "-fx-border-radius: 5;" + "-fx-margin-top: 50;");
            hbox.setPrefHeight(25);
            vbox.setMargin(vbox, new Insets(100, 100, 100, 100));
            vbox.getChildren().add(hbox);
        }
        return vbox;
    }
    
    public VBox getCards() {
        VBox vbox = new VBox();
        vbox.setSpacing(25);
        Label ro = new Label("Fares");
        ro.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        vbox.getChildren().add(ro);
        vbox.setMaxHeight(500.0);
        
        HBox hbox = new HBox();
        Text label = new Text();
        label.setText("Charlie Card Fare");
        Text label2 = new Text();
        label2.setText(String.valueOf(new CharlieCard().passFee));
        hbox.getChildren().addAll(label, label2);
        vbox.getChildren().add(hbox);
        return vbox;
    }
            
}
