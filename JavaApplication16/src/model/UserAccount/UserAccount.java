/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.UserAccount;

import model.ApplicationSystem.UserProfile.Profile;
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
    private final String useraccountId;
    
    public UserAccount() {
      super();
      this.counter++;
      this.useraccountId = "account"+this.counter;
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
    }
    
    
}
