/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Customer;

import java.util.ArrayList;
import model.ApplicationSystem.UserProfile.Profile;
import model.Transaction.RideBooking;
import model.Transaction.Transaction;
import model.UserAccount.UserAccount;

/**
 *
 * @author Nidhi Raghavendra
 */
public class Customer {
    UserAccount useraccount;
    ArrayList<Transaction> transactions;
    ArrayList<RideBooking> bookings;
    
    public Customer() {
        this.transactions = new ArrayList<Transaction>();
        this.bookings = new ArrayList<RideBooking>();
    }

    public UserAccount getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(UserAccount useraccount) {
        this.useraccount = useraccount;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<RideBooking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<RideBooking> bookings) {
        this.bookings = bookings;
    }
    
    
}
