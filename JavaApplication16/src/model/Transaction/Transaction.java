/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Transaction;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Nidhi Raghavendra
 */
public class Transaction {
    public static int count;
    private int transactionId;
    private long cardDetails;
    private double amount;
    private String status;
    private LocalDate transactionDate;
    private String transactionType;
    
    public Transaction() {
        count++;
        this.transactionId = count;
        this.transactionDate = LocalDate.now();
    }

    public int getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public long getCardDetails() {
        return cardDetails;
    }

    public void setCardDetails(long cardDetails) {
        this.cardDetails = cardDetails;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = LocalDate.now();
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    
    
}
