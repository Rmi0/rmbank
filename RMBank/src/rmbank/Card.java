/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmbank;

/**
 *
 * @author Rolo
 */
public class Card {
    private int id;
    private long number;
    private int accnum;
    private boolean blocked;
    private int pin;
    
    public Card(int id, long number, int accnum, boolean blocked, int pin) {
        this.id = id;
        this.number = number;
        this.accnum = accnum;
        this.blocked = blocked;
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public long getNumber() {
        return number;
    }

    public int getAccnum() {
        return accnum;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public int getPin() {
        return pin;
    }
    
    
}
