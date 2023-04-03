/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Commute;

import java.util.ArrayList;

/**
 *
 * @author Nidhi Raghavendra
 */
public class CommuteDirectory {
    private ArrayList<Train> trains;
    private ArrayList<Bus> buses;
    private ArrayList<Ride> rides;
    
    public CommuteDirectory() {
        this.trains = new ArrayList<Train>();
        this.buses = new ArrayList<Bus>();
        this.rides = new ArrayList<Ride>();
    }

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public void setTrains(ArrayList<Train> trains) {
        this.trains = trains;
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }

    public void setBuses(ArrayList<Bus> buses) {
        this.buses = buses;
    }

    public ArrayList<Ride> getRides() {
        return rides;
    }

    public void setRides(ArrayList<Ride> rides) {
        this.rides = rides;
    }
    
    public Bus addBus() {
        Bus bus = new Bus();
        this.buses.add(bus);
        return bus;
    }
    
    public Train addTrain() {
        Train train = new Train();
        this.trains.add(train);
        return train;
    }
    
    public Ride addRide() {
        Ride ride = new Ride();
        this.rides.add(ride);
        return ride;
    }
}
