/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ApplicationSystem;

import java.util.ArrayList;

import model.Commute.CommuteDirectory;
import model.Commute.Ride;
import model.Customer.CustomerDirectory;
import model.RideAgent.RideAgenDirectory;
import model.RideAgent.RideAgent;
import model.Role.MBTAAdminRole;
import model.Role.SystemAdminRole;
import model.Routes.Location;
import model.Routes.Route;
import model.UserAccount.UserAccount;
import model.UserAccount.UserAccountDirectory;

/**
 *
 * @author Nidhi Raghavendra
 */
public class ApplicationSystem {
    public static ApplicationSystem appSystem;
    private boolean userLoggedIn;
    private UserAccount loggedInUserAccount = new UserAccount();
    private UserAccountDirectory userdirectory = new UserAccountDirectory();
    private ArrayList<Route> routes;
    private ArrayList<Location> locations;
    private CustomerDirectory customerDirectory;
    private RideAgenDirectory rideAgentDirectory;
    private CommuteDirectory commuteDirectory;
    
    public static ApplicationSystem getInstance() {
        if(appSystem == null) {
            appSystem = new ApplicationSystem();
            return appSystem;
        }
        return appSystem;
    }
    
    public ApplicationSystem() {
        UserAccount u = userdirectory.createNewUserAccount("admin", "admin", "Administrator", "raghunidhi22@gmail.com");
        u.setRole(new SystemAdminRole());
        UserAccount mu = userdirectory.createNewUserAccount("mbta", "mbta", "MBTA Manager", "manager.mbta@bostonmbta.org");
        mu.setRole(new MBTAAdminRole());
        
        this.routes = new ArrayList<Route>();
        this.locations = new ArrayList<Location>();
        this.customerDirectory = new CustomerDirectory();
        this.rideAgentDirectory = new RideAgenDirectory();
        this.commuteDirectory = new CommuteDirectory();
        userLoggedIn = false;
    }

    public UserAccountDirectory getUserdirectory() {
        return userdirectory;
    }

    public void setUserdirectory(UserAccountDirectory userdirectory) {
        this.userdirectory = userdirectory;
    }

    public boolean isUserLoggedIn() {
        return this.userLoggedIn;
    }

    public void setUserLoggedIn(boolean userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    public  UserAccount getLoggedInUserAccount() {
        return this.loggedInUserAccount;
    }

    public  void setLoggedInUserAccount(UserAccount loggedInUserAccount) {
        this.loggedInUserAccount = loggedInUserAccount;
    }

  

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public CustomerDirectory getCustomerDirectory() {
        return customerDirectory;
    }

    public void setCustomerDirectory(CustomerDirectory customerDirectory) {
        this.customerDirectory = customerDirectory;
    }

    public RideAgenDirectory getRideAgentDirectory() {
        return rideAgentDirectory;
    }

    public void setRideAgentDirectory(RideAgenDirectory rideAgentDirectory) {
        this.rideAgentDirectory = rideAgentDirectory;
    }
    
    
    public CommuteDirectory getCommuteDirectory() {
		return commuteDirectory;
	}

	public void setCommuteDirectory(CommuteDirectory commuteDirectory) {
		this.commuteDirectory = commuteDirectory;
	}

	public void populateRides() {
		System.out.println("Inside populate rides ::::::::::::::::::::::" + this.rideAgentDirectory.getAgentlist().size());
		
		// the rides in the system are vehicles pre-defined with a set of routes that they service. Each ride also has an agent
		// #of rides = # of ride agents in the system
		// if a ride is booked, we will mark ride agent as unavailable until he finishes ride.
		for(RideAgent agent: this.rideAgentDirectory.getAgentlist()) {
			Ride ride = this.commuteDirectory.addRide();
			ride.setName("Carpool" + ride.getID());
			ride.setAgent(agent);
			ride.getCommuteRoutes();
		}
	}
	
	public static void main(String a[]){
        ApplicationSystem app = ApplicationSystem.getInstance();
        System.out.println(app.getUserdirectory());
        if(app.getUserdirectory().checkIfExists("admin", "admin")) {
            System.out.print("kjfk");
        }
    }
    
    
}
