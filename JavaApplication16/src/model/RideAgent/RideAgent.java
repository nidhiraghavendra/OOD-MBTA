/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.RideAgent;

import java.time.LocalDate;
import java.util.Date;
import model.ApplicationSystem.UserProfile.Profile;
import model.UserAccount.UserAccount;

/**
 *
 * @author Nidhi Raghavendra
 */
public class RideAgent {
    UserAccount useraccount;
    private boolean available;
    private String license;
    private LocalDate joinDate;
    Vehicle vehicle;
    
    public RideAgent() {
        this.available = true;
        this.vehicle = new Vehicle();
        this.useraccount = new UserAccount();
    }

    public UserAccount getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(UserAccount useraccount) {
        this.useraccount = useraccount;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    
    
}
