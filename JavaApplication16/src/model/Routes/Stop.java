package model.Routes;

import java.util.ArrayList;

public class Stop {
	 String name;
	 boolean isActive;
	 ArrayList<Combination> routestop = new ArrayList<>(); // List with Bus Name and its next Stop 
	 Stop(String name)
	 {this.name = name;
	 	this.isActive = true;
	 }
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
