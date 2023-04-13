/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.UserAccount;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nidhi Raghavendra
 */
public class UserAccountDirectory {

    private ObservableList<UserAccount> useraccountlist;

    public UserAccountDirectory() {
        this.useraccountlist = FXCollections.observableArrayList();
    }

    public ObservableList<UserAccount> getUseraccountlist() {
        return this.useraccountlist;
    }

  

    public UserAccount createNewUserAccount(String username, String password, String name, String email) {
        System.out.println("Reached ------------------");
        UserAccount user = new UserAccount();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);

        this.useraccountlist.add(user);
        return user;
    }

    public boolean checkIfExists(String username, String password) {
//        System.out.println("exists method -- " + username + password);
        if (this.useraccountlist.size() > 0) {
            for (UserAccount user : useraccountlist) {
                System.out.println("exists method -- " + user.getUsername() + user.getPassword());
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    public UserAccount findUser(String username, String password) {
        for (UserAccount user : this.useraccountlist) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
