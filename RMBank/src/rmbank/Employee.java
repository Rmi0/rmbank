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
public class Employee {
    private String lastName;
    private String firstName;
    private int id;

    public Employee(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
}
