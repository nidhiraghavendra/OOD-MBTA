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
    private static double cardBalance;
    private String cardStatus;
    
    public Card() {
        this.cardID = this.cardType+counter++;
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

    public static double getCardBalance() {
        return cardBalance;
    }

    public static void setCardBalance(double cardBalance) {
        Card.cardBalance = cardBalance;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }
    
    
    public abstract void calculateCardBalance();
    public abstract void setLowerLimit();
    public abstract void rechargeCard();
}
