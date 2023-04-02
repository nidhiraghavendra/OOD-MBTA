/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Routes;

/**
 *
 * @author Nidhi Raghavendra
 */
public class Location {
    private static int locationID = 0;
    private String name;
        
    public Location() {
        locationID++;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
