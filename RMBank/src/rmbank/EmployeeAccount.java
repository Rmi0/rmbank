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
public class EmployeeAccount {
    private int id;
    private int empid;
    private String username;
    private char[] password;
    
    public EmployeeAccount(int id, int empid, String username,String password) {
        this.id = id;
        this.empid = empid;
        this.username = username;
        this.password = password.toCharArray();
    }

    public int getId() {
        return id;
    }

    public int getEmpid() {
        return empid;
    }

    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }
    
}
