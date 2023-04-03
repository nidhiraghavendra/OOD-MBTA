package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.ApplicationSystem.ApplicationSystem;
import model.Commute.Ride;
import model.Routes.Location;
import model.Routes.Route;
import model.UserAccount.UserAccount;

public class RideFXMLController implements Initializable{
	
	
	ApplicationSystem app;
    ObservableList<Ride> ride;
    
    
	 @FXML
	 Pane anchorPane;

     @FXML
     TableView<Route> rideTableView;
	
	 @FXML
	 TableColumn<Route, String> colSource;
	 @FXML
	 TableColumn<Route, String> colDestination;
	 @FXML
	 TableColumn<Route, Double> colDistance;
	 @FXML
	 TableColumn<Route, Double> colDuration;

	 
	 
	 
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
	        
	        for (Ride ride: app.getCommuteDirectory().getRides()) {
	        	ObservableList<Route> items = ride.getRoute();
	        	rideTableView.setItems(items);
	        }
	        
	    }
	
	 
	 
	 

}
