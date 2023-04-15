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

    public ArrayList<RideAgent> getAgentlist() {
        return agentlist;
    }

    public void setAgentlist(ArrayList<RideAgent> agentlist) {
        this.agentlist = agentlist;
    }

    public RideAgent createRideAgent() {
        RideAgent rideAgent = new RideAgent();
        this.agentlist.add(rideAgent);
        return rideAgent;
    }

    public boolean findAgent(String email, String license, String plate) {
        for (RideAgent a : this.agentlist) {
            if (a.getUseraccount().getEmail().equals(email) || a.getLicense().equals(license) || a.getVehicle().getNumberPlate().equals(plate)) {
                return true;
            }
        }
        return false;
    }
}
