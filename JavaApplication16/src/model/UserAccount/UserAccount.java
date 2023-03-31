/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.UserAccount;

import model.ApplicationSystem.UserProfile.Profile;
import model.Card.CharlieCard;
import model.Card.RidePass;
import model.Role.Role;

/**
 *
 * @author Nidhi Raghavendra
 */
public class UserAccount extends Profile {
    private static int counter = 0;
    private String username;
    private String password;
    private Role role;
    private String roleType;
    private String useraccountId;
    private CharlieCard charlieCard;
    private RidePass ridePass;
    
    public UserAccount() {
      super();
      this.counter++;
      this.useraccountId = "account"+this.counter;
      this.charlieCard = new CharlieCard();
      this.ridePass = new RidePass();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
        this.roleType = role.getRoleType();
        this.useraccountId += role.getRoleType();
    }

    public CharlieCard getCharlieCard() {
        return charlieCard;
    }

    public void setCharlieCard(CharlieCard charlieCard) {
        this.charlieCard = charlieCard;
    }

    public RidePass getRidePass() {
        return ridePass;
    }

    public void setRidePass(RidePass ridePass) {
        this.ridePass = ridePass;
    }

    
    public String getUseraccountId() {
        return useraccountId;
    }

    public void setUseraccountId(String useraccountId) {
        this.useraccountId = useraccountId;
    }

    public String getRoleType() {
        return this.roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
    
    
    
}
