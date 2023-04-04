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
    private static int transactionId;
    private long cardDetails;
    private double amount;
    private String status;
    private LocalDate transactionDate;
    private String transactionType;
    
    public Transaction() {
        transactionId++;
    }

    public static int getTransactionId() {
        return transactionId;
    }

    public static void setTransactionId(int transactionId) {
        Transaction.transactionId = transactionId;
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
