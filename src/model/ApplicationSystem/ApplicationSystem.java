/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.ApplicationSystem;

import model.Role.MBTAAdminRole;
import model.Role.SystemAdminRole;
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
    
    public static void main(String a[]){
        ApplicationSystem app = ApplicationSystem.getInstance();
        System.out.println(app.getUserdirectory());
        if(app.getUserdirectory().checkIfExists("admin", "admin")) {
            System.out.print("kjfk");
        }
    }
    
    
}
