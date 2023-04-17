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
