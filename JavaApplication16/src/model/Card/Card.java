/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Card;

import java.io.ByteArrayInputStream;

/**
 *
 * @author Nidhi Raghavendra
 */
public abstract class Card {

    private String cardID;
    private static int counter = 0;
    private String cardType;
    private ByteArrayInputStream QRCodePath;
    private double cardBalance;
    private String cardStatus;
    public double lowerLimit;
    public double passFee;

    public Card() {
        this.cardID = this.cardType + counter++;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public ByteArrayInputStream getQRCodePath() {
        return QRCodePath;
    }

    public void setQRCodePath(ByteArrayInputStream QRCodePath) {
        this.QRCodePath = QRCodePath;
    }

    public double getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(double cardBalance) {
        this.cardBalance = cardBalance;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public void calculateCardBalance() {
        if (this.getCardBalance() <= lowerLimit) {
            this.setCardBalance(lowerLimit);
            this.setCardStatus("Deactivated");
        } else {
            double balance = this.getCardBalance();
            if (balance > lowerLimit && balance <= 0.0) {
                this.setCardStatus("Recharge Card");
            } else {
                this.setCardBalance(balance);
                this.setCardStatus("Active");
            }
        }
    }

    public abstract void setLowerLimit();

    public abstract void setPassFee();

    public void rechargeCard(double amount) {
        double balance = this.getCardBalance();
        this.setCardBalance(balance + amount);
        if (this.getCardBalance() > 0.0) {
            this.setCardStatus("Active");
        } 

    }

    public void resetCard() {
        this.cardBalance = 0.0;
    }

    public boolean deductAmount(double amount) {
        if(amount == 0.0) {
            amount = passFee;
        }
        if (this.cardBalance >= 0.0) {
            this.cardBalance -= amount;
            this.cardBalance = Math.round(this.cardBalance*100.0)/100.0;
            return true;
        } else if(this.cardBalance < 0.0 && this.cardBalance > this.lowerLimit && this.cardBalance-amount >= this.lowerLimit) {
            this.cardBalance -= amount;
            this.cardBalance = Math.round(this.cardBalance*100.0)/100.0;
            return true;
        } 
                
        return false;
    }

    public double getPassFee() {
        return passFee;
    }

    public void setPassFee(double passFee) {
        this.passFee = passFee;
    }
    
    
}
