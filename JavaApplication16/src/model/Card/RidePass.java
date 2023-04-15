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
public class RidePass extends Card{

    public RidePass() {
        super();
    }

    @Override
    public void setLowerLimit() {
        super.lowerLimit = -20.0;
    }

    @Override
    public void setPassFee() {
        super.passFee = 2.75;
    }
    
}
