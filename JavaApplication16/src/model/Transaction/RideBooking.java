/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Transaction;

import model.Commute.Ride;
import model.Routes.Location;
import model.Routes.Route;

/**
 *
 * @author Nidhi Raghavendra
 */
public class RideBooking {
    private static int bookingId = 0;
    private Ride ride;
    private Route rideRoute;
    private double rideTotal;
    private double duration;
    private String status;
    private Transaction transaction;
    
    public RideBooking() {
        bookingId++;
    }
}
