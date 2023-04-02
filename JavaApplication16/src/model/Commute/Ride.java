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
    
    ArrayList<Route> route = new ArrayList<Route>();
    
    public ArrayList<Route> getCommuteRoutes() {
    	String file = "C:\\Users\\chand\\OOD PROJECT\\ride.csv";
    	String line = "";
    	
        try {
        	BufferedReader reader = new BufferedReader(new FileReader(file));
        	while((line = reader.readLine()) != null) {
        		String[] row = line.split(",");
        		
        		Route rideRoute = new Route();
        		Location location = new Location();
        		location.setName("Brookline");
        		rideRoute.setSource(location);
        		rideRoute.getDestination().setName("Copley");
        		rideRoute.setDistance(2);
        		rideRoute.setDuration(10);
        		rideRoute.getCurrentLocation().setName("ABC");
        		
        		route.add(rideRoute);
        		
    		}
        	
        }catch(Exception e) {
        	e.printStackTrace();
          }
    	
        return route;
        
        
        
    }

	public ArrayList<Route> getRoute() {
		return route;
	}

	public void setRoute(ArrayList<Route> route) {
		this.route = route;
	}
    
}