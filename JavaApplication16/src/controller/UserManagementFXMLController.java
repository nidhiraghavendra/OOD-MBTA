/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.ApplicationSystem.ApplicationSystem;
import model.ApplicationSystem.Validation;
import model.Customer.Customer;
import model.RideAgent.RideAgent;
import model.UserAccount.UserAccount;

/**
 * FXML Controller class
 *
 * @author Nidhi Raghavendra
 */
public class UserManagementFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ApplicationSystem app;
    ObservableList<UserAccount> users;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    ButtonType buttontype = new ButtonType("OK", ButtonData.OK_DONE);
    @FXML
    Pane anchorPane;

    @FXML
    TableView<UserAccount> useraccountTableView;

    @FXML
    TableView<UserAccount> customerTableView;

    @FXML
    TableView<UserAccount> rideTableView;

    @FXML
    TableView<UserAccount> transTableView;

    @FXML
    TableColumn<UserAccount, String> colAccountId;
    @FXML
    TableColumn<UserAccount, String> colId;
    @FXML
    TableColumn<UserAccount, String> colIdR;

    @FXML
    TableColumn<UserAccount, String> colEmail;
    @FXML
    TableColumn<UserAccount, String> colEmailC;
    @FXML
    TableColumn<UserAccount, String> colEmailR;

    @FXML
    TableColumn<UserAccount, String> colUsername;
    @FXML
    TableColumn<UserAccount, String> colUsernameC;
    @FXML
    TableColumn<UserAccount, String> colUsernameR;

    @FXML
    TableColumn<UserAccount, String> colRole;

    @FXML
    TableColumn<UserAccount, String> colName;
    @FXML
    TableColumn<UserAccount, String> colNameC;
    @FXML
    TableColumn<UserAccount, String> colNameR;

    @FXML
    TabPane adminTabPane;

    @FXML
    Tab accountTab;
    @FXML
    Tab customerTab;
    @FXML
    Tab rideTab;
    @FXML
    Tab transactionTab;

    @FXML
    TextField fieldName;
    @FXML
    TextField fieldEmail;
    @FXML
    TextField license;
    @FXML
    TextField brand;

    @FXML
    DatePicker picker;

    @FXML
    Button addBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.app = ApplicationSystem.getInstance();
        users = this.app.getUserdirectory().getUseraccountlist();
        System.out.println("In User management controller :: " + app.getCustomerDirectory().getCustomerlist().size());

    }

    @FXML
    private void accountTabClicked(Event e) {
        // populate the table
        System.out.print("IN ACCT TAB :: ");
        colAccountId.setCellValueFactory(new PropertyValueFactory<>("useraccountId"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("roleType"));
        ObservableList<UserAccount> items = app.getUserdirectory().getUseraccountlist();
        useraccountTableView.setItems(items);
    }

    @FXML
    private void customerTabClicked(Event e) {
        colId.setCellValueFactory(new PropertyValueFactory<>("useraccountId"));
        colEmailC.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUsernameC.setCellValueFactory(new PropertyValueFactory<>("username"));
        colNameC.setCellValueFactory(new PropertyValueFactory<>("name"));

        ObservableList<UserAccount> items = FXCollections.observableArrayList();
        for (Customer c : app.getCustomerDirectory().getCustomerlist()) {
            items.add(c.getUseraccount());
            System.out.print(c);
        }
        System.out.println(items + " :: ITEMS");
        if (items != null) {
            customerTableView.setItems(items);
        }
    }

    @FXML
    private void rideTabClicked(Event e) {
        colIdR.setCellValueFactory(new PropertyValueFactory<>("useraccountId"));
        colEmailR.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUsernameR.setCellValueFactory(new PropertyValueFactory<>("username"));
        colNameR.setCellValueFactory(new PropertyValueFactory<>("name"));
        ObservableList<UserAccount> items = FXCollections.observableArrayList();
        for (UserAccount user : app.getUserdirectory().getUseraccountlist()) {
            if (user.getRoleType().equals("agent")) {
                items.add(user);
            }
        }

        rideTableView.setItems(items);
    }

    @FXML
    private void transactionTabClicked(Event e) {

    }

    @FXML
    private void addBtnClicked(ActionEvent e) {
        String email = fieldEmail.getText();
        String name = fieldName.getText();
        String l = license.getText();
        String b = brand.getText();
        LocalDate date = picker.getValue();

        Validation validate = new Validation();

        if (!validate.validateEmail(email)) {
            displayAlert("Invalid email address");
        }       
        if(!validate.validateName(name)) {
            displayAlert("Invalid name");
        }
        RideAgent ride = app.getRideAgentDirectory().createRideAgent();
        ride.getUseraccount().setEmail(email);
        ride.getUseraccount().setName(name);
        ride.getUseraccount().setRoleType("agent");
        ride.getUseraccount().setUsername(name);
        ride.setLicense(l);
        ride.getVehicle().setCarBrand(b);

        app.getUserdirectory().getUseraccountlist().add(ride.getUseraccount());
    }

    private void displayAlert(String message) {
        alert.setTitle("");
        alert.setContentText(message);
        alert.getDialogPane().getButtonTypes().add(buttontype);
        
        alert.showAndWait();
    }
}
