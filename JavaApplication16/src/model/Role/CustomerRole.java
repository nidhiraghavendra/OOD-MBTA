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
public class CustomerRole extends Role {

    @Override
    public String getRoleFXMLURL() {
       return "./view/MainFXML.fxml";
    }

    @Override
    public String getRoleType() {
        return "customer";
    }
    
}
