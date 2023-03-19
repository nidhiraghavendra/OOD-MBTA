/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Role;

/**
 *
 * @author Nidhi Raghavendra
 */
public abstract class Role {
    public static String roles[] = {"customer", "admin"};
    
    public static String[] getRoles() {
        return roles;
    }
    
    public abstract String getRoleFXMLURL(); 
    public abstract String getRoleType();
}
