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
    private Button btnBook;
    

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
    }

    private void populateTable() {
        // populate the table
        System.out.print("IN RIDE TAB :: ");
        colSource.setCellValueFactory(new PropertyValueFactory<>("source"));
        colDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        colDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        ObservableList<Route> items = app.getCommuteDirectory().getAllRoutes();
        rideTableView.setItems(items);

    }
    
    @FXML
    private void btnBookOnClick(ActionEvent event) {
//    	Stage stage = (Stage) anchorPane.getScene().getWindow();
    	Route route = rideTableView.getSelectionModel().getSelectedItem();
    	AlertType type = AlertType.INFORMATION;
    	Alert alert = new Alert(type, "");
    	alert.initModality(Modality.APPLICATION_MODAL);
//    	alert.initOwner(stage);
    	alert.getDialogPane().setContentText("Your ride has been booked from: " + route.getSource() + " to " + route.getDestination());
    	alert.showAndWait();
    	
    	
    	
    	  UserAccount user = this.app.getLoggedInUserAccount();
          Customer customer = this.app.getCustomerDirectory().findACustomer(user);
          Transaction transaction = customer.addTransaction();
          RideBooking rb = customer.addRideBooking();
                     rb.setSource(route.getSource());
                     rb.setDestination(route.getDestination());
                     rb.setDuration(route.getDuration());
                     rb.setDistance(route.getDistance());
                     rb.setRideTotal(1.2*(route.getDistance()));
                     rb.setStatus("Booked");
                     
                     boolean deduction = user.getRidePass().deductAmount(rb.getRideTotal());
  
                     transaction.setAmount(rb.getRideTotal());
                     transaction.setTransactionType("ride");
                     if(deduction) {
                    	 transaction.setStatus("Paid");
                     }else {
                    	 transaction.setStatus("Failed");
                     }
                     
                     
                 
                     
                     sourceHistoryCol.setCellValueFactory(new PropertyValueFactory<>("source"));
                     destinationHistoryCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
                     distanceHistoryCol.setCellValueFactory(new PropertyValueFactory<>("distance"));
                     durationHistoryCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
                     totalCol.setCellValueFactory(new PropertyValueFactory<>("rideTotal"));
                     statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
                     


                     rideHistoryTableView.setItems(customer.getBookings());
                     
   }
    
    @FXML
    private void rideHistoryTabClicked(Event e) {
        // populate the table
//    	Route route = rideTableView.getSelectionModel().getSelectedItem();
//    	  UserAccount user = this.app.getLoggedInUserAccount();
//          Customer customer = this.app.getCustomerDirectory().findACustomer(user);
//          RideBooking rb = customer.addRideBooking();
//                     rb.setSource(route.getSource());
//                     rb.setDestination(route.getDestination());
//                     rb.setDuration(route.getDuration());
//                     rb.setDistance(route);
    }
    
    
    
    
   
   
     
    
  

}
