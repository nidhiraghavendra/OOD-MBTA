package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ApplicationSystem.ApplicationSystem;
import model.Card.Card;
import model.Commute.Ride;
import model.Customer.Customer;
import model.Routes.Location;
import model.Routes.Route;
import model.Transaction.RideBooking;
import model.Transaction.Transaction;
import model.UserAccount.UserAccount;

public class RideFXMLController implements Initializable {

    ApplicationSystem app;
    ObservableList<Ride> ride;
    ObservableList<RideBooking> bookings;
    ObservableList<Transaction> transactions;

    @FXML
    Pane anchorPane;

    @FXML
    TableView<Route> rideTableView;

    @FXML
    TableView<RideBooking> rideHistoryTableView;

    @FXML
    TableColumn<Route, String> colSource;
    @FXML
    TableColumn<Route, String> colDestination;
    @FXML
    TableColumn<Route, Double> colDistance;
    @FXML
    TableColumn<Route, Double> colDuration;

    @FXML
    TableColumn<Route, String> sourceHistoryCol;
    @FXML
    TableColumn<Route, String> destinationHistoryCol;
    @FXML
    TableColumn<Route, Double> distanceHistoryCol;
    @FXML
    TableColumn<Route, Double> durationHistoryCol;
    @FXML
    TableColumn<Route, Double> totalCol;
    @FXML
    TableColumn<Route, Double> statusCol;
    @FXML
    TableColumn<RideBooking, String> agentHistoryCol;

    @FXML
    private Button btnBook;

    @FXML
    private Button markBtn;

    @FXML
    private ComboBox<Location> sourcebox;

    @FXML
    private ComboBox<Location> destinationbox;

    @FXML
    private Button searchbtn;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        this.app = ApplicationSystem.getInstance();
        this.app.populateRides();
        try {
            this.app.populateLocations();
        } catch (IOException ex) {
            Logger.getLogger(RideFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        ride = this.app.getUserdirectory().getUseraccountlist();
//        System.out.println("In User management controller :: " + app.getCustomerDirectory().getCustomerlist().size());
        populateTable();
        populateDp();
    }

    private void populateTable() {
        // populate the table
        System.out.print("IN RIDE TAB :: ");
        colSource.setCellValueFactory(new PropertyValueFactory<>("source"));
        colDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        colDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        ObservableList<Route> items = app.getCommuteDirectory().getAllRoutes();
        rideTableView.getItems().clear();
        rideTableView.setItems(items);

        sourceHistoryCol.setCellValueFactory(new PropertyValueFactory<>("source"));
        destinationHistoryCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        distanceHistoryCol.setCellValueFactory(new PropertyValueFactory<>("distance"));
        durationHistoryCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("rideTotal"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        agentHistoryCol.setCellValueFactory(new PropertyValueFactory<>("ride"));
    }

    @FXML
    private void btnBookOnClick(ActionEvent event) {
//    	Stage stage = (Stage) anchorPane.getScene().getWindow();
        Route route = rideTableView.getSelectionModel().getSelectedItem();
        AlertType type = AlertType.INFORMATION;
        Alert alert = new Alert(type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
//    	alert.initOwner(stage);

        UserAccount user = this.app.getLoggedInUserAccount();
        Customer customer = this.app.getCustomerDirectory().findACustomer(user);

        Ride assigned = this.app.getCommuteDirectory().assignARide(route);
        if (assigned != null) {
            RideBooking rb = customer.addRideBooking();
            rb.setSource(route.getSource());
            rb.setDestination(route.getDestination());
            rb.setDuration(route.getDuration());
            rb.setDistance(route.getDistance());
            rb.setRideTotal(Math.round(1.2 * (route.getDistance()) * 100.0) / 100.0);
            rb.setStatus("Booked");
            rb.setRide(assigned);
            boolean deduction = user.getRidePass().deductAmount(rb.getRideTotal());
            Transaction transaction = customer.addTransaction();

            transaction.setAmount(rb.getRideTotal());
            transaction.setTransactionType("ride");
            if (deduction) {
                transaction.setStatus("Paid");
            } else {
                transaction.setStatus("Failed");
            }
            alert.getDialogPane().setContentText("Your ride has been booked from: " + route.getSource() + " to " + route.getDestination());
            alert.showAndWait();
        } else {
            alert.getDialogPane().setContentText("Looking for nearby drivers.. please wait! ");
            alert.showAndWait();
        }

        rideHistoryTableView.setItems(customer.getBookings());

    }

    @FXML
    private void markBtnClicked(Event e) {
        // populate the table
        RideBooking booking = rideHistoryTableView.getSelectionModel().getSelectedItem();

        this.app.getCommuteDirectory().releaseRide(booking.getRide());
        UserAccount user = this.app.getLoggedInUserAccount();
        Customer customer = this.app.getCustomerDirectory().findACustomer(user);
        for (RideBooking b : customer.getBookings()) {
            System.out.print(b + " ;;; " + booking);
            if (b.equals(booking)) {
                b.setStatus("Completed");
            }
        }
        rideHistoryTableView.setItems(customer.getBookings());
        AlertType type = AlertType.INFORMATION;
        Alert alert = new Alert(type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.getDialogPane().setContentText("Thank you for riding with us!");
        alert.showAndWait();
    }

    @FXML
    private void rideHistoryClicked(Event e) {
        UserAccount user = this.app.getLoggedInUserAccount();
        Customer customer = this.app.getCustomerDirectory().findACustomer(user);
        rideHistoryTableView.setItems(customer.getBookings());
    }

    private void populateDp() {
        ArrayList<Location> locations = this.app.getLocations();
        ObservableList<Location> items = FXCollections.observableArrayList(locations);

        sourcebox.getItems().clear();
        sourcebox.setItems(items);
        destinationbox.getItems().clear();
        destinationbox.setItems(items);
    }

    @FXML
    private void clicked(ActionEvent event) {
        rideTableView.getItems().clear();
        Location source = sourcebox.getSelectionModel().getSelectedItem();
        Location destination = destinationbox.getSelectionModel().getSelectedItem();
        ObservableList<Route> results = FXCollections.observableArrayList();
        if (!source.getName().equals(destination.getName())) {
            for (Route r : this.app.getCommuteDirectory().getAllRoutes()) {
                if (r.getSource().equals(source) && r.getDestination().equals(destination)) {
                    results.add(r);
                    break;
                }
            }

            rideTableView.setItems(results);
        }
    }

    @FXML
    private void rideclicked(Event evn) {
        populateTable();
    }
}