/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Commute;

import java.util.ArrayList;
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
    
    public Ride() {
        this.agent = new RideAgent();
    }
    
    public ArrayList<Route> getCommuteRoutes() {
        return null;
    }
}
