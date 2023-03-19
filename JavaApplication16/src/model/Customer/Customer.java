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

/**
 *
 * @author Nidhi Raghavendra
 */
public class Customer {
    Profile profile;
    ArrayList<Transaction> transactions;
    ArrayList<RideBooking> bookings;
    
    public Customer() {
        this.transactions = new ArrayList<Transaction>();
        this.bookings = new ArrayList<RideBooking>();
    }
}
