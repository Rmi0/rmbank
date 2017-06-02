/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmbank.database;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import rmbank.Account;
import rmbank.Card;
import rmbank.Customer;
import rmbank.Employee;
import rmbank.EmployeeAccount;

/**
 *
 * @author Richard
 */
public class BankDB {

    private String url = "jdbc:mysql://localhost"; // 127.0.0.1
    private String username = "root";
    private String password = "";
    private String dbname = "rmbank";
    private String driver = "com.mysql.jdbc.Driver";

    public ResultSet executeQuery(String cmd) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ResultSet rs = null;

        Connection conn = getConnection();
        if (conn != null) {
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(cmd);
        }
        return rs;
    }

    public void execute(String cmd) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Connection conn = getConnection();
        if (conn != null) {
            Statement statement = conn.createStatement();
            statement.execute(cmd);
        }
        closeConnection(conn);
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName(driver).newInstance();
        return DriverManager.getConnection(url + "/" + dbname, username, password);
    }

    public List getAllCustomers() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Customer> customers = new ArrayList<>();
        ResultSet rs = executeQuery("select * from customer INNER JOIN customerdetails ON customer.id = customerid");
        while (rs.next()) {
            String active = rs.getString("active");
            if (active.equals("Y")) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String city = rs.getString("city");
                String address = rs.getString("address");
                Date dob = rs.getDate("dob");
                customers.add(new Customer(id, firstName, lastName, city, address, dob));
            }
        }
        return customers;
    }

    public List getAllAccounts() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Account> accounts = new ArrayList<>();
        ResultSet rs = executeQuery("select * from account");
        while (rs.next()) {
            int number = rs.getInt("number");
            int customerid = rs.getInt("customerid");
            double balance = rs.getDouble("balance");
            String stringBlocked = rs.getString("blocked").toUpperCase();
            boolean blocked;
            blocked = stringBlocked.equals("Y") ? true : false;

            accounts.add(new Account(number, customerid, balance, blocked));
        }
        return accounts;
    }

    public Customer getCustomerByID(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Customer> customers = getAllCustomers();
        for (Customer c : customers) {
            int retrievedID = c.getId();
            if (id == retrievedID) {
                return c;
            }
        }
        return null;
    }

    public List getAccountsByCustomer(Customer c) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Account> accounts = getAllAccounts();
        List<Account> newAccounts = new ArrayList<>();
        
        for (Account a : accounts) {
            if (a.equals(null)) continue;
            if (a.getCustomerid() == c.getId()) {
                newAccounts.add(a);
            }
        }
        return newAccounts;
    }
    
    public Account getAccountByNumber(int number) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (Account a : ((List<Account>)getAllAccounts())) {
            if (a.getNumber() == number) return a;
        }
        return null;
    }

    public Employee getEmployeeByUsername(String username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "select * from employee"
                + " inner join employeeaccount on employee.id = empid"
                + " where employeeaccount.username like ?";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String firstName = rs.getString("firstname");
            String lastName = rs.getString("lastname");

            return new Employee(id, firstName, lastName);
        }
        return null;
    }

    public EmployeeAccount getEmployeeAccountByUsername(String username) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "select * from employeeaccount where username like ?";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            int empid = rs.getInt("empid");
            String password = rs.getString("password");
            return new EmployeeAccount(id, empid, username, password);
        }
        return null;
    }

    public void changePassword(String username, String password) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "update employeeaccount set password=? where username "
                + "like ?";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setString(1, password);
        ps.setString(2, username);
        ps.execute();
    }

    public void closeConnection(Connection conn) throws SQLException {
        if (conn == null) {
            return;
        }
        conn.close();
    }
    
    public void newAccount(int customerID) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Account> accounts = this.getAllAccounts();
        Random r = new Random();
        int accNumber = 1000000000 + r.nextInt(999999999);
        while (accNumber % 11 != 0 || accounts.contains(accNumber)) {
            accNumber = 1000000000 + r.nextInt(999999999);
        }
        String query = "insert into account (number, customerid, balance) values (?, ?, 0)";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setInt(1, accNumber);
        ps.setInt(2, customerID);
        ps.execute();
    }
    
    public void changeFunds(int accNum, double funds) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (funds == 0) return;
        
        double balance = 0;
        String query = "select blocked, balance from account where number = ?";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setInt(1, accNum);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (rs.getString("blocked").equals("Y")) return;
            balance = rs.getDouble("balance");
        }
        if (funds < 0 && -funds > balance) return;
        query = "update account SET balance = balance + ? WHERE number = ?";
        ps = getConnection().prepareStatement(query);
        ps.setDouble(1, funds);
        ps.setInt(2, accNum);
        ps.execute();
    }
    
    public void setAccountState(int accNum, boolean blocked) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String stringBlocked = blocked?"Y":"N";
        String query = "update account SET blocked = ? WHERE number = ?";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setString(1, stringBlocked);
        ps.setInt(2, accNum);
        ps.execute();
    }
    
    public void createCustomer(String firstName, String lastName, String city, String address, java.util.Date dob) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "insert into customer (firstname,lastname,active) VALUES (?,?,'Y')";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.execute();
        
        int customerid = 1;
        
        query = "select MAX(id) AS customerid from customer";
        ResultSet rs = executeQuery(query);
        if (rs.next()) customerid = rs.getInt("customerid");
        
        query = "insert into customerdetails (customerid,city,address,dob) VALUES (?,?,?,?)";
        ps = getConnection().prepareStatement(query);
        ps.setInt(1, customerid);
        ps.setString(2, city);
        ps.setString(3, address);
        Date dateofbirth = new Date(dob.getTime());
        ps.setDate(4, dateofbirth);
        ps.execute();
    }
    
    public void deleteCustomer(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "update customer SET active = 'N' WHERE id = ?";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setInt(1, id);
        ps.execute();
    }
    
    public void createCard(long number, int accnum, int pin)throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "insert into card (number, accnum, pin) VALUES (?,?,?)";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setLong(1, number);
        ps.setInt(2, accnum);
        ps.setInt(3, pin);
        ps.execute();
    }
    
    public List<Card> getCards(int customerid) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<Card> cards = new ArrayList<>();
        String query = "select card.id as id, card.number as number,"
                + " card.accnum as accnum, card.blocked as blocked, card.pin"
                + " as pin from card inner join account ON"
                + " card.accnum = account.number inner join customer on"
                + " account.customerid = customer.id where customer.id = ?";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setInt(1, customerid);
        ResultSet rs = ps.executeQuery();
        System.out.println(ps.toString());
        while (rs.next()) {
            int id = rs.getInt("id");
            long number = rs.getLong("number");
            int accnum = rs.getInt("accnum");
            boolean blocked = rs.getString("blocked").equals("N")?false:true;
            int pin = rs.getInt("pin");
            cards.add(new Card(id, number, accnum, blocked, pin));
        }
        return cards;
    }
    
    public boolean doesCardExist(long number)  throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "select number from card where number="+number;
        ResultSet rs = this.executeQuery(query);
        return rs.next();
    }
}
