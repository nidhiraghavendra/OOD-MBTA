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
        this.announcementslist = FXCollections.observableArrayList();
//        read(Paths.get("Objectsavefile.ser"));
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
        for(Location l: this.locations) {
            if(l.getName().equals(name)) {
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

    public Graph initalizeGraph()
    {	graph.addStops();
    	return graph;
    }
    public void addAnnouncement(Announcement announcement)
    {
    	
    	announcementslist.add(announcement);
    }
//    public void write(ObservableList<Announcement> personObservalble) {
//        try {
//        	
//            // write object to file
//        	ArrayList<Announcement> announcmentlist = new ArrayList<>();
//        	for(int i=0;i<personObservalble.size();i++)
//        	{
//        		announcmentlist.add(personObservalble.get(i));	
//        	}
//        	 FileOutputStream writeData = new FileOutputStream("peopledata.ser");
//        	    ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
//
//        	    writeStream.writeObject(announcmentlist);
//        	    writeStream.flush();
//        	    writeStream.close();
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//    private static ObservableList<Announcement> read() throws IOException, ClassNotFoundException {
//    	  FileInputStream readData;
//		try {
//			readData = new FileInputStream("peopledata.ser");
//    	    ObjectInputStream readStream = new ObjectInputStream(readData);
//
//    	    ArrayList<Announcement> people2 = (ArrayList<Announcement>) readStream.readObject();
//    	    readStream.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//    	   
//        return FXCollections.emptyObservableList();
//    }

}
