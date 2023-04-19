/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ApplicationSystem;

import model.Routes.Graph;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Announcement.Announcement;
import model.Commute.CommuteDirectory;
import model.Commute.Ride;
import model.Customer.CustomerDirectory;
import model.RideAgent.RideAgenDirectory;
import model.RideAgent.RideAgent;
import model.Role.MBTAAdminRole;
import model.Role.SystemAdminRole;
import model.Routes.Graph;
import model.Routes.Location;
import model.Routes.Route;
import model.UserAccount.UserAccount;
import model.UserAccount.UserAccountDirectory;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Graph graph;
    public Graph getGraph() {
		return graph;
	}

	private ObservableList<Announcement> announcementslist;

    public static ApplicationSystem getInstance() {
        if (appSystem == null) {
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
        this.graph = new Graph();
        initalizeGraph();
        this.announcementslist = FXCollections.observableArrayList();
        
        populateAnnouncement();
        try {
            populateLocations();
//        read(Paths.get("Objectsavefile.ser"));
        } catch (IOException ex) {
            Logger.getLogger(ApplicationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Announcement> getAnnouncementslist() {
        return announcementslist;
    }

    public void setAnnouncementslist(ObservableList<Announcement> announcementslist) {
        this.announcementslist = announcementslist;
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

    public UserAccount getLoggedInUserAccount() {
        return this.loggedInUserAccount;
    }

    public void setLoggedInUserAccount(UserAccount loggedInUserAccount) {
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

    public Location findALocationByName(String name) {
        for (Location l : this.locations) {
            if (l.getName().equals(name)) {
                return l;
            }
        }

        return null;
    }

    public void populateRides() {
        System.out.println("Inside populate rides ::::::::::::::::::::::" + this.rideAgentDirectory.getAgentlist().size());

        // the rides in the system are vehicles pre-defined with a set of routes that they service. Each ride also has an agent
        // #of rides = # of ride agents in the system
        // if a ride is booked, we will mark ride agent as unavailable until he finishes ride.
        for (RideAgent agent : this.rideAgentDirectory.getAgentlist()) {
            Ride ride = this.commuteDirectory.addRide();
            ride.setName("Carpool" + ride.getID());
            ride.setAgent(agent);
            ride.getCommuteRoutes();
        }
    }

    public void populateLocations() throws FileNotFoundException, IOException {
        String line = "";
        BufferedReader reader = new BufferedReader(new FileReader("locationsData"));

        while ((line = reader.readLine()) != null) {
            Location location = new Location();
            location.setName(line);
            location.setZipcode(21 + location.getLocationID());

            this.locations.add(location);
        }
    }

    public static void main(String a[]) {
        ApplicationSystem app = ApplicationSystem.getInstance();
        System.out.println(app.getUserdirectory());
        if (app.getUserdirectory().checkIfExists("admin", "admin")) {
            System.out.print("kjfk");
        }

    }

    public Graph initalizeGraph() {
        graph.addStops();
        return graph;
    }

    public void addAnnouncement(Announcement announcement) {

        announcementslist.add(announcement);
    }

    public void populateAnnouncement() {
        Announcement a = new Announcement("Welcome to MBTA portal", "MBTA Pass Programs deliver flexibility and convenience for institutions and large groups using the T. MBTA Pass Programs can help seasoned commuters and students alike access the transit system with ease");
        this.addAnnouncement(a);
        Announcement b = new Announcement("FAQS", "How do I check the balance on my CharlieTicket or CharlieCard? \n  Login into the app to check the balance on your Charlie Card.");
        this.addAnnouncement(b);
        Announcement c = new Announcement("FAQS", "Are there peak-hour fares on the MBTA? \n  No. All fares are the same no matter what time of day or day of the week you ride.");
        this.addAnnouncement(c);
        Announcement d = new Announcement("FAQS","Can I pay for more than 1 person using the same CharlieCard or CharlieTicket? \n Yes, if you use stored value (pay as you go) on a CharlieCard. You can pay for up to 4 one-way fares at once using a single CharlieCard at the same station. You can also store 1 pass of any type on the same card.");
    this.addAnnouncement(d);
    }
}
