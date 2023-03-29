/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.RideAgent;

import java.util.ArrayList;

/**
 *
 * @author Nidhi Raghavendra
 */
public class RideAgenDirectory {
    private ArrayList<RideAgent> agentlist;
    
    public RideAgenDirectory() {
        this.agentlist = new ArrayList<RideAgent>();
    }
    
    public RideAgent createRideAgent() {
        RideAgent rideAgent = new RideAgent();
        this.agentlist.add(rideAgent);
        return rideAgent;
    }
}
