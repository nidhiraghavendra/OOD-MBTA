/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Commute;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ApplicationSystem.ApplicationSystem;
import model.Routes.Location;
import model.Routes.Route;

/**
 *
 * @author Nidhi Raghavendra
 */
public class CommuteDirectory {

    private ArrayList<Train> trains;
    private ArrayList<Bus> buses;
    private ArrayList<Ride> rides;

    private ObservableList<Route> allRoutes;

    public CommuteDirectory() {
        this.trains = new ArrayList<Train>();
        this.buses = new ArrayList<Bus>();
        this.rides = new ArrayList<Ride>();
        this.allRoutes = FXCollections.observableArrayList();
    }

    public ObservableList<Route> getAllRoutes() {
        String[][] matrix = new String[17][17];
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("locationsData"));
            int i = 1;
            while ((line = reader.readLine()) != null) {

                matrix[i][0] = line;
                matrix[0][i] = line;
                i++;

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ride.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ride.class.getName()).log(Level.SEVERE, null, ex);
        }
        ApplicationSystem app = ApplicationSystem.getInstance();
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][0] != matrix[0][j]) {
                    matrix[i][j] = String.valueOf(Math.abs(j - i)+1);
                    Route route = new Route();
                    Location source = app.findALocationByName(matrix[i][0]);
                    route.setSource(source);
                    Location location = app.findALocationByName(matrix[0][j]);
                    route.setDestination(location);

                    // duration is calculated at random
                    route.setDistance(Double.valueOf(Math.abs(j - i) + 1));
                    // duration is twice the distance time in minutes
                    route.setDuration(Double.valueOf(Math.abs(j - i) + 1) * 2);
                    // price = 1.2$ per mile
                    this.allRoutes.add(route);
                } else {
                    matrix[i][j] = "0";
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }

            System.out.println();
        }

        return allRoutes;
    }

    public void setAllRoutes(ObservableList<Route> allRoutes) {
        this.allRoutes = allRoutes;
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
        bus.setType("bus");
        this.buses.add(bus);
        return bus;
    }

    public Train addTrain() {
        Train train = new Train();
        train.setType("train");
        this.trains.add(train);
        return train;
    }

    public Ride addRide() {
        Ride ride = new Ride();
        ride.setType("ride");
        this.rides.add(ride);
        return ride;
    }

    // assign a ride for the booking
    public Ride assignARide(Route route) {
        for (Ride ride : this.rides) {
            if (ride.getAgent().isAvailable()) {
                for (Route r : ride.getCommuteRoutes()) {
                    if (r.getSource().equals(route.getSource()) && r.getDestination().equals(route.getDestination())) {
                        ride.setBooked(true);
                        ride.getAgent().setAvailable(false);
                        return ride;
                    }
                }
            }
        }

        return null;
    }

    public void releaseRide(Ride ride) {
        ride.setBooked(false);
        ride.getAgent().setAvailable(true);
    }

    public ObservableList<Ride> showAllRides(Location source, Location destination) {
        ObservableList<Ride> availableRides = FXCollections.observableArrayList();
        for (Ride ride : this.rides) {
            for (Route route : ride.getCommuteRoutes()) {
                if (ride.getAgent().isAvailable()) {
                    if (route.getSource().equals(source) && route.getDestination().equals(destination)) {
                        availableRides.add(ride);
                    }
                }

            }
        }

        return availableRides;
    }
}
