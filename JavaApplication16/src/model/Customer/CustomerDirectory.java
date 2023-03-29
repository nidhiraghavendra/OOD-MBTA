/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Customer;

import java.util.ArrayList;
import model.UserAccount.UserAccount;

/**
 *
 * @author Nidhi Raghavendra
 */
public class CustomerDirectory {
    private ArrayList<Customer> customerlist;
    
    public CustomerDirectory() {
        this.customerlist = new ArrayList<Customer>();
    }
    
    public Customer createCustomer(UserAccount useraccount) {
        Customer customer = new Customer();
        customer.setUseraccount(useraccount);
        this.customerlist.add(customer);
        return customer;
    }
}
