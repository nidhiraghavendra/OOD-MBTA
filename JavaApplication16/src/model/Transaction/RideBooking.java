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
    private double distance;
    private double rideTotal;
    private double duration;
    private String status;
    private Transaction transaction;
    private Location source;
    private Location destination;
    
    public RideBooking() {
    	
        bookingId++;
        this.source = new Location();
        this.destination = new Location();
        this.transaction = new Transaction();

    }

//	public RideBooking() {
//		// TODO Auto-generated constructor stub
//	}

	public static int getBookingId() {
		return bookingId;
	}

	public static void setBookingId(int bookingId) {
		RideBooking.bookingId = bookingId;
	}

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

	public Route getRideRoute() {
		return rideRoute;
	}

	public void setRideRoute(Route rideRoute) {
		this.rideRoute = rideRoute;
	}

	public double getRideTotal() {
		return rideTotal;
	}

	public void setRideTotal(double rideTotal) {
		this.rideTotal = rideTotal;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public Location getSource() {
		return source;
	}

	public void setSource(Location source) {
		this.source = source;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}



	

}
