/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmbank.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import rmbank.database.BankDB;

/**
 *
 * @author Rolo
 */
public class Login extends JFrame {

    private JTextField userField;
    private JPasswordField passField;

    public Login() {
        super("RMBank Login Form");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 250);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username");
        userLabel.setSize(150, 20);
        userLabel.setLocation(50, 20);

        JLabel passLabel = new JLabel("Password");
        passLabel.setSize(150, 20);
        passLabel.setLocation(50, 60);

        this.userField = new JTextField();
        this.userField.setSize(240, 20);
        this.userField.setLocation(210, 20);

        this.passField = new JPasswordField();
        this.passField.setSize(240, 20);
        this.passField.setLocation(210, 60);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setSize(400, 50);
        loginButton.setLocation(50, 100);

        JButton cancelButton = new JButton("CANCEL");
        cancelButton.setSize(400, 50);
        cancelButton.setLocation(50, 160);

        panel.add(userLabel);
        panel.add(passLabel);
        panel.add(this.userField);
        panel.add(this.passField);
        panel.add(loginButton);
        panel.add(cancelButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean accessgranted = false;
                try {
                    Connection conn = new BankDB().getConnection();
                    if (conn == null) {
                        throw new NullPointerException("Connection = null");
                    }
                    PreparedStatement ps = conn.prepareStatement("select empid, password from employeeaccount where username like ?");
                    ps.setString(1, userField.getText());

                    ResultSet rs = ps.executeQuery();
                    int empid = 0;
                    if (rs.next()) {
                        empid = rs.getInt("empid");
                        String dbpass = rs.getString("password");
                        String pass = "";
                        for (char c : passField.getPassword()) {
                            pass = pass + c;
                            c = 0;
                        }
                        if (dbpass.equals(pass)) {
                            accessgranted = true;
                        }
                    }
                    /////// LOGGING ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    if (accessgranted) {
                        ps = conn.prepareStatement("insert into employeeloginhistory (empid) VALUES (?)");
                        ps.setInt(1, empid);

                       /* Date date = new Date();
                        java.sql.Date sqldate = new java.sql.Date(date.getTime());
                        ps.setDate(2, sqldate);*/

                        ps.execute();
                        ps.close();
                        new Manage(userField.getText()).setVisible(true);
                        dispose();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.add(panel);
    }
}
