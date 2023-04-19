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

    private boolean isBooked;
    private RideAgent agent;
    private ObservableList<Route> route;

    public Ride() {
        this.agent = new RideAgent();
        this.route = FXCollections.observableArrayList();
    }
    public ObservableList<Route> getCommuteRoutes() {
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
 }
    catch (FileNotFoundException ex) {
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
 route.setDestination(location);// duration is calculated at random
route.setDistance(Double.valueOf(Math.abs(j - i) + 1)); // duration is twice the distance time in minutes
route.setDuration(Double.valueOf(Math.abs(j - i) + 1) * 2);// price = 1.2$ per mile
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
}
return this.route;
}
    public ObservableList<Route> getRoute() {
        return route;
    }

    public void setRoute(ObservableList<Route> route) {
        this.route = route;
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

    @Override
    public String toString() {
        return this.agent.getUseraccount().getName();
    }
}
