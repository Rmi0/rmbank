/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmbank;

import java.util.*;

/**
 *
 * @author Rolo
 */
public class Customer {
    private String lastName;
    private String firstName;
    private int id;
    private String city;
    private String address;
    private Date dob;

    public Customer(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = "-----";
        this.address = "-----";
        this.dob = null;
    }
    
    public Customer(int id, String firstName, String lastName, String city, String address, Date dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.address = address;
        this.dob = dob;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Date getDob() {
        return dob;
    }
    
     
}
