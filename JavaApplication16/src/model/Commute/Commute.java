/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Commute;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Routes.Route;

/**
 *
 * @author Nidhi Raghavendra
 */
public class Commute {
    private int ID;
    public static int counter = 0;
    
    private String type;
    private boolean isActive = false;
    private String name;
    private String VIN;
    private double price;
    
    public ObservableList<Route> commuteRoutes;
    
    public Commute() {
        this.ID = counter++;
        this.commuteRoutes = FXCollections.observableArrayList();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Commute.counter = counter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public ObservableList<Route> getCommuteRoutes() {
        return commuteRoutes;
    }

    public void setCommuteRoutes(ObservableList<Route> commuteRoutes) {
        this.commuteRoutes = commuteRoutes;
    }
    
    private void addRoute(Route route) {
        this.commuteRoutes.add(route);
    }
    
}
