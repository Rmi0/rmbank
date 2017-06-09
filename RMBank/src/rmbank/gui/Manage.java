/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmbank.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import rmbank.Account;
import rmbank.Card;
import rmbank.Customer;
import rmbank.Employee;
import rmbank.EmployeeAccount;
import rmbank.database.BankDB;

/**
 *
 * @author Richard
 */
public class Manage extends JFrame {

    private static Manage instance = null;
    private String user;

    private JComboBox customerBox;
    private JFrame optionframe;
    private JPasswordField oldpass;
    private JPasswordField newpass;
    private JPasswordField confirmpass;
    private JLabel resultlabel;
    private JTabbedPane infoholder;
    private CustomerInfo general;
    private AccountInfo accounts;
    private CardInfo cards;

    public Manage(String username) {
        super("RMBank Management Form");
        this.user = username;
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getTitle().equals("RMBank Management Form")) {
                    setTitle("Logged in as " + user);
                } else {
                    setTitle("RMBank Management Form");
                }
            }
        });
        timer.start();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JMenuBar menubar = new JMenuBar();
        menubar.setSize(this.getWidth(), 20);

        JMenu accountmenu = new JMenu("Account");
        accountmenu.setSize(50, 20);
        JMenuItem changepass = new JMenuItem("Change Password");
        JMenuItem logout = new JMenuItem("Logout");
        
        infoholder = new JTabbedPane();
        infoholder.setSize(700,300);
        infoholder.setLocation(50, 200);
        this.general = new CustomerInfo();
        infoholder.add("General", this.general);
        this.accounts = new AccountInfo();
        this.accounts.getAccountBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object selectedItem = accounts.getAccountBox().getSelectedItem();
                    if (selectedItem == null) {
                        accounts.setAllMissing();
                        return;
                    }
                    int number = (int)selectedItem;
                    Account a = new BankDB().getAccountByNumber(number);
                    if (a == null) {
                        accounts.setAllMissing();
                        return;
                    }
                    accounts.setBalance(a.getBalance());
                    accounts.setBlocked(a.isBlocked());
                } catch (Exception ex) {ex.printStackTrace();}
            }
        });
        infoholder.add("Accounts", this.accounts);
        this.cards = new CardInfo();
        infoholder.add("Cards", this.cards);
        
        panel.add(infoholder);
        
        //Password change form
        optionframe = new JFrame("Password change form");
        optionframe.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        optionframe.setAlwaysOnTop(true);
        JPanel optionbox = new JPanel();
        optionbox.setSize(optionframe.getSize());
        optionframe.add(optionbox);
        optionframe.setSize(400, 250);
        optionframe.setLocationRelativeTo(null);
        optionbox.setLayout(null);
        optionframe.setResizable(false);

        JLabel oldlabel = new JLabel("Old password: ");
        oldlabel.setLocation(20, 20);
        oldlabel.setSize(150, 20);
        JLabel newlabel = new JLabel("New password: ");
        newlabel.setLocation(20, 40);
        newlabel.setSize(150, 20);
        JLabel confirmlabel = new JLabel("Confirm new password: ");
        confirmlabel.setLocation(20, 60);
        confirmlabel.setSize(150, 20);
        oldpass = new JPasswordField();
        oldpass.setLocation(170, 20);
        oldpass.setSize(150, 20);
        newpass = new JPasswordField();
        newpass.setLocation(170, 40);
        newpass.setSize(150, 20);
        confirmpass = new JPasswordField();
        confirmpass.setLocation(170, 60);
        confirmpass.setSize(150, 20);
        resultlabel = new JLabel("", JLabel.CENTER);
        resultlabel.setLocation(20, 195);
        resultlabel.setSize(360, 20);
        resultlabel.setOpaque(true);
        resultlabel.setBackground(Color.DARK_GRAY);

        JButton proceed = new JButton("Proceed");
        proceed.setLocation(20, 100);
        proceed.setSize(360, 40);

        proceed.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(newpass.getPassword()).equals(String.valueOf(confirmpass.getPassword()))) {
                    try {
                        EmployeeAccount empacc = new BankDB().getEmployeeAccountByUsername(user);
                        if (String.valueOf(oldpass.getPassword()).equals(String.valueOf(empacc.getPassword()))) {
                            new BankDB().changePassword(user, String.valueOf(newpass.getPassword()));
                            
                            resultlabel.setForeground(Color.GREEN);
                            resultlabel.setText("Password has been changed!");
                            
                            return;
                        }
                    } catch (Exception ex) {ex.printStackTrace();}
                }
                resultlabel.setForeground(Color.RED);
                resultlabel.setText("Invalid credentials!");
            }
        });

        JButton cancel = new JButton("Cancel");
        cancel.setLocation(20, 150);
        cancel.setSize(360, 40);

        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                optionframe.setVisible(false);
            }
        });

        optionbox.add(oldlabel);
        optionbox.add(newlabel);
        optionbox.add(confirmlabel);
        optionbox.add(oldpass);
        optionbox.add(newpass);
        optionbox.add(confirmpass);
        optionbox.add(resultlabel);
        optionbox.add(proceed);
        optionbox.add(cancel);
        changepass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultlabel.setText("");
                optionframe.setVisible(true);
                optionframe.requestFocus();
            }
        });
        
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });
        accountmenu.add(changepass);
        accountmenu.add(new JPopupMenu.Separator());
        accountmenu.add(logout);
        menubar.add(accountmenu);

        JLabel userLabel = new JLabel();
        userLabel.setSize(200, 20);
        userLabel.setLocation(20, 30);

        try {
            Employee emp = new BankDB().getEmployeeByUsername(username);
            String firstName = emp.getFirstName();
            String lastName = emp.getLastName();
            userLabel.setText("User: " + firstName + " " + lastName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.customerBox = new JComboBox();
        this.customerBox.setSize(200, 20);
        this.customerBox.setLocation(20, 60);
        this.loadCustomers();
        
        customerBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGeneralDetails();
                loadAccounts();
                loadCards();
                infoholder.setSelectedIndex(0);
            }
        });
        
        JButton newCustomerButton = new JButton("Create New Customer");
        newCustomerButton.setSize(540,20);
        newCustomerButton.setLocation(240, 60);
        
        newCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerCreator("RMBank Customer Creator").setVisible(true);
            }
        });
        
        panel.add(menubar);
        panel.add(userLabel);
        panel.add(this.customerBox);
        panel.add(newCustomerButton);
        this.add(panel);
        
        instance = this;
    }

    public void loadCustomers() {
        try {
            this.customerBox.removeAllItems();
            int i = 0;
            for (Object customer : new BankDB().getAllCustomers()) {
                int id = ((Customer) customer).getId();
                String firstName = ((Customer) customer).getFirstName();
                String lastName = ((Customer) customer).getLastName();
                this.customerBox.insertItemAt(id + " | " + firstName + " " + lastName, i);
                i++;
            }
            if (this.customerBox.getItemCount() >= 1)
                this.customerBox.setSelectedIndex(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void loadAccounts() { 
        try {
            if (this.customerBox == null) throw new NullPointerException("customerBox = null");
            if (this.accounts == null) throw new NullPointerException("account info = null");
            int id = Integer.parseInt(String.valueOf(customerBox.getSelectedItem()).split(" ")[0]);
            Customer c = new BankDB().getCustomerByID(id);
            if (c == null) throw new NullPointerException("Customer not found");
            this.accounts.setAccountBox(c);
        } catch (Exception ex) {ex.printStackTrace();}
        
    }
    
    public void loadGeneralDetails() {
        try {
            int id = Integer.parseInt(String.valueOf(customerBox.getSelectedItem()).split(" ")[0]);
            Customer c = new BankDB().getCustomerByID(id);
            general.setID(c.getId());
            general.setName(c.getFirstName()+" "+c.getLastName());
            general.setCity(c.getCity());
            general.setAddress(c.getAddress());
            general.setDob(c.getDob());
        } catch(Exception ex) {ex.printStackTrace();}
    }
    
    public void loadCards() {
        try {
            int id = Integer.parseInt(String.valueOf(customerBox.getSelectedItem()).split(" ")[0]);
            System.out.println("id");
            List<Card> cards = new BankDB().getCards(id);
            //this.cards = new CardInfo();
            this.cards.load(cards);
        } catch(Exception ex) {ex.printStackTrace();}
    }
    
    public static Manage getInstance() {
        return instance;
    }

    public AccountInfo getAccounts() {
        return accounts;
    }
    
    
}
