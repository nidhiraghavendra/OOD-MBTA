/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ApplicationSystem.ApplicationSystem;
import model.ApplicationSystem.Validation;
import model.Card.RidePass;
import model.Customer.Customer;
import model.Transaction.CardValidation;
import model.Transaction.Transaction;
import model.UserAccount.UserAccount;

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
    Customer customer;
    ObservableList<String> cards = FXCollections.observableArrayList();

    Alert alert = new Alert(Alert.AlertType.ERROR);
    ButtonType buttontype = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);

    @FXML
    private ComboBox<String> cardsComboBox;

    @FXML
    private Label username;

    @FXML
    private Label accountnumber;

    @FXML
    private Label charlieCardBalance;

    @FXML
    private Label ridePassBalance;

    @FXML
    private Label passStatus;
    @FXML
    private Label charlieStatus;

    @FXML
    private TextField cardnumber;
    @FXML
    private TextField cvv;
    @FXML
    private TextField month;
    @FXML
    private TextField year;
    @FXML
    private TextField name;

    @FXML
    private Button rechargeBtn;
    
    @FXML
    private Button payBtn;

    @FXML
    private TextField amount;

    @FXML
    Tab profileTab;
    @FXML
    Tab transactionTab;
    
    @FXML
    TableView<Transaction> tranTable;
    @FXML
    TableColumn<Transaction, String> tid;
    @FXML
    TableColumn<Transaction, String> tdate;
    @FXML
    TableColumn<Transaction, String> tstatus;
    @FXML
    TableColumn<Transaction, String> tprice;
    @FXML
    TableColumn<Transaction, String> ttype;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.app = ApplicationSystem.getInstance();
        this.customer = this.app.getCustomerDirectory().findACustomer(this.app.getLoggedInUserAccount());
        cards.add("Charlie Card");
        cards.add("Ride Card");
        cardsComboBox.setItems(cards);

        populateForm();
    }

    @FXML
    public void profileTabClicked(Event e) {
        populateForm();
    }

    public void populateForm() {
        UserAccount user = this.app.getLoggedInUserAccount();
        username.setText(user.getName());
        accountnumber.setText(user.getUseraccountId());
        
        user.getCharlieCard().calculateCardBalance();
        user.getRidePass().calculateCardBalance();
        
        charlieCardBalance.setText(String.valueOf(user.getCharlieCard().getCardBalance()));
        ridePassBalance.setText(String.valueOf(user.getRidePass().getCardBalance()));

        passStatus.setText(user.getRidePass().getCardStatus());
        charlieStatus.setText(user.getCharlieCard().getCardStatus());

        if (user.getRidePass().equals("Active")) {
            passStatus.setStyle("-fx-color: green;");
        }
    }

    @FXML
    public void rechargeBtnClicked(ActionEvent e) {
        String cardtype = cardsComboBox.getSelectionModel().getSelectedItem();
        String fullName = name.getText();
        Validation validate = new Validation();
        try {
            if (!validate.validateName(fullName)) {
                String cardNumber = cardnumber.getText();
                CardValidation cardValid = new CardValidation();

                if (cardValid.intakeNumber(Long.valueOf(cardNumber))) {
                    if (validate.validateMonth(month.getText()) && validate.validateYear(year.getText())) {
                        if (validate.validateCVV(cvv.getText())) {
                            if (cardtype.contains("Charlie")) {
                                this.app.getLoggedInUserAccount().getCharlieCard().rechargeCard(Double.valueOf(amount.getText()));
                                Transaction t = this.customer.addTransaction();
                                t.setCardDetails(Long.valueOf(cardNumber));
                                t.setAmount(Double.valueOf(amount.getText()));
                                t.setStatus("Completed");
                                t.setTransactionType("charlie card");
                                populateForm();
                            } else if (cardtype.contains("Ride")) {
                                this.app.getLoggedInUserAccount().getRidePass().rechargeCard(Double.valueOf(amount.getText()));
                                Transaction t = this.customer.addTransaction();
                                t.setCardDetails(Long.valueOf(cardNumber));
                                t.setAmount(Double.valueOf(amount.getText()));
                                t.setStatus("Completed");
                                t.setTransactionType("ride pass");
                                populateForm();
                            }

                        } else {
                            displayAlert("CVV incorrect.");
                        }
                    } else {
                        displayAlert("Invalid expiration date");
                    }
                } else {
                    displayAlert("Invalid card number");
                }
            } else {
                displayAlert("Invalid Name");
            }
        } catch(NumberFormatException ex) {
            ex.printStackTrace();
            displayAlert("Check the data inputs.");
        }
    }

    private void displayAlert(String message) {
        alert.setTitle("");
        alert.setContentText(message);
        alert.getDialogPane().getButtonTypes().add(buttontype);

        alert.showAndWait();
    }
    
    @FXML
    public void transactionTabClicked(Event e) {
        tid.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        tdate.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
        tstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tprice.setCellValueFactory(new PropertyValueFactory<>("amount"));
        ttype.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        
        ObservableList<Transaction> items = this.customer.getTransactions();
        if(items != null || items.size() > 0) {
            tranTable.setItems(items);
        }
    }
    
    @FXML
    public void payBtnClicked(Event e) {
        Transaction t = tranTable.getSelectionModel().getSelectedItem();
        UserAccount user = this.app.getLoggedInUserAccount();
        RidePass pass = user.getRidePass();
        
        boolean b = pass.deductAmount(t.getAmount());
        if(b) {
            t.setStatus("Paid");
            tranTable.setItems(this.customer.getTransactions());
        } else {
            t.setStatus("Failed");
            displayAlert("Insufficient balance to make the payment.");
        }
    }
}
