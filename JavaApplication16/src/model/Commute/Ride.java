/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Commute;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ApplicationSystem.ApplicationSystem;
import model.RideAgent.RideAgent;
import model.Routes.Location;
import model.Routes.Route;

/**
 *
 * @author Nidhi Raghavendra
 */
public class Ride extends Commute {

    private static int capacity;
    private boolean isBooked;
    private RideAgent agent;
    private ObservableList<Route> route;

    public Ride() {
        this.agent = new RideAgent();
        this.route = FXCollections.observableArrayList();
    }

    public ObservableList<Route> getCommuteRoutes() {
//    	String file = "C:\\Users\\chand\\OOD PROJECT\\OOD-MBTA\\JavaApplication16\\src\\ride.csv";
//    	String line = "";
//    	
//        try {
//        	BufferedReader reader = new BufferedReader(new FileReader(file));
//        	while((line = reader.readLine()) != null) {
//        		String[] row = line.split(",");
//        		
//        		System.out.println(row[1]);
//        		
//        		Route rideRoute = new Route();
//        		Location location = new Location();
////        		rideRoute.getID();
//        		location.setName(row[0]);
//        		rideRoute.setSource(location);
//        		rideRoute.getDestination().setName(row[1]);
//        		rideRoute.setDistance(Double.valueOf(row[2]));
//        		rideRoute.setDuration(Double.valueOf(row[3]));
//        		rideRoute.getCurrentLocation().setName(row[4]);
//        		
//        		this.route.add(rideRoute);
//        		
//    		}
//        	
//        }catch(Exception e) {
//        	e.printStackTrace();
//          }

        // populating routes using a 2D array
        String[][] matrix = new String[16][16];
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("locationsData"));
            int i = 0;
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

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (i != j) {
                    matrix[i][j] = String.valueOf(i + j);

                    Route route = new Route();
                    ApplicationSystem app = ApplicationSystem.getInstance();
                    try {
                        app.populateLocations();
                    } catch (IOException ex) {
                        Logger.getLogger(Ride.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    for (Location location : app.getLocations()) {
                        if (location.getName().equals(matrix[0][j])) {
                            route.setDestination(location);
                            Location source = app.findALocationByName(matrix[i][0]); 
                            route.setSource(source);
                            break;
                        }
                    }
                    // duration is calculated at random
                    route.setDistance(Double.valueOf(i + j));
                    // duration is twice the distance time in minutes
                    route.setDuration(Double.valueOf(i + j) * 2);
                    // price = 1.2$ per mile
                    this.route.add(route);
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

        return this.route;

    }

    public ObservableList<Route> getRoute() {
        return route;
    }

    public void setRoute(ObservableList<Route> route) {
        this.route = route;
    }

    public static int getCapacity() {
        return capacity;
    }

    public static void setCapacity(int capacity) {
        Ride.capacity = capacity;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public RideAgent getAgent() {
        return agent;
    }

    public void setAgent(RideAgent agent) {
        this.agent = agent;
    }

    public static void main(String a[]) {
        Ride r = new Ride();
        r.getCommuteRoutes();

        for (Route route : r.getRoute()) {
            System.out.print(route.getDuration() + " ");
        }
    }

}
