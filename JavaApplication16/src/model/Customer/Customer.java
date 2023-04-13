/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Customer;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ApplicationSystem.UserProfile.Profile;
import model.Routes.Location;
import model.Routes.Route;
import model.Transaction.RideBooking;
import model.Transaction.Transaction;
import model.UserAccount.UserAccount;

/**
 *
 * @author Nidhi Raghavendra
 */
public class Customer {
    UserAccount useraccount;
    ObservableList<Transaction> transactions;
    ObservableList<RideBooking> bookings;
    private Location source;
    private Location destination;
    private Route distance;
    private double duration;
    
    
    public Customer() {
        this.useraccount = new UserAccount();
        this.transactions = FXCollections.observableArrayList();
        this.bookings = FXCollections.observableArrayList();
    }

    public UserAccount getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(UserAccount useraccount) {
        this.useraccount = useraccount;
    }

    public ObservableList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ObservableList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public ObservableList<RideBooking> getBookings() {
        return bookings;
    }

    public void setBookings(ObservableList<RideBooking> bookings) {
        this.bookings = bookings;
    }
    
    public Transaction addTransaction() {
        Transaction t = new Transaction();
        this.transactions.add(t);
        return t;
    }
    
    public RideBooking addRideBooking() {
    	
	
		RideBooking rb = new RideBooking();
    	this.bookings.add(rb);
		return rb;
    	
    }
}
