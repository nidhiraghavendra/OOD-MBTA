/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Transaction;

import java.util.Date;

/**
 *
 * @author Nidhi Raghavendra
 */
public class Transaction {
    private static int transactionId;
    private long cardDetails;
    private double amount;
    private String status;
    private Date transactionDate;
    private String travelType;
    
    public Transaction() {
        transactionId++;
    }
}
