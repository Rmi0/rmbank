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
public class Account {
    private int number;
    private int customerid;
    private double balance;
    private boolean blocked;
    
    public Account(int number, int customerid, double balance, boolean blocked) {
        this.number = number;
        this.customerid = customerid;
        this.balance = balance;
        this.blocked = blocked;
    }

    public int getNumber() {
        return number;
    }

    public int getCustomerid() {
        return customerid;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isBlocked() {
        return blocked;
    }
    
    
}
