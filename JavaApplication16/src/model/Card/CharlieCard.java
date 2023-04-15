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
public class CharlieCard extends Card {

    public CharlieCard() {
        super();
    }

    @Override
    public void setLowerLimit() {
        super.lowerLimit = -10.0;
    }

    @Override
    public void setPassFee() {
        super.passFee = 2.40;
    }
}
