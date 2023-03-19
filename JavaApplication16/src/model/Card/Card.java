/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Card;

/**
 *
 * @author Nidhi Raghavendra
 */
public abstract class Card {
    private String cardID;
    private static int counter = 0;
    private String cardType;
    private String QRCodePath;
    private static double cardBalance;
    private String cardStatus;
    
    public Card() {
        this.cardID = this.cardType+counter++;
    }
        
    public abstract void calculateCardBalance();
    public abstract void setLowerLimit();
    public abstract void rechargeCard();
}
