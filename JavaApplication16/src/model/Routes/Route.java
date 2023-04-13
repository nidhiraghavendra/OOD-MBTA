/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Routes;

import model.Transaction.Transaction;

/**
 *
 * @author Nidhi Raghavendra
 */
public class Route {
    private static int ID = 0;
    private Location source;
    private Location destination;
    private double distance;
    private double duration;
    private Location currentLocation;
    private double price;
    private Transaction transaction;
    
    public Route() {
        this.ID++;
        this.source = new Location();
        this.destination = new Location();
        this.currentLocation = new Location();
        this.transaction = new Transaction();
    }

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Route.ID = ID;
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

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price += distance*1.2;
    }

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
    
    
    
}
