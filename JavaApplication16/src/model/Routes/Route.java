/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Routes;


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
    
    public Route() {
        this.ID++;
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
    
    
    
}
