/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmbankatm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Rolo
 */
public class BankDB {
    
    private static BankDB instance = null;
    
    private String url = "jdbc:mysql://localhost"; // 127.0.0.1
    private String username = "root";
    private String password = "";
    private String dbname = "rmbank";
    private String driver = "com.mysql.jdbc.Driver";
    
    private BankDB() {
        
    }
    
    public static BankDB getInstance() {
        if (instance == null) instance = new BankDB();
        return instance;
    }
    
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
    
    public void closeConnection(Connection conn) throws SQLException {
        if (conn == null) {
            return;
        }
        conn.close();
    }
    
    public boolean cardExists(long number) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "select * from card where number=?";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setLong(1, number);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
     
    public boolean isCardBlocked(long number) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (!cardExists(number)) throw new SQLException("Card does not exist");
        String query = "select blocked from card where number=?";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setLong(1, number);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("blocked").equalsIgnoreCase("Y")?true:false;
        }
        return true;
    }
    
    public void wrongPIN(long number) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "insert into invalidentries (cardnum) VALUES (?)";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setLong(1,number);
        ps.execute();
        
        query = "select count(*) as rows from invalidentries WHERE cardnum = ?";
        ps = getConnection().prepareStatement(query);
        ps.setLong(1, number);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt("rows");
        if (count >= 3) blockCard(number);
    }
    
    public void blockCard(long number) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "update card set blocked=? where number=?";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setString(1, "Y");
        ps.setLong(2, number);
        ps.execute();
        
        this.removeInvalidEntries(number);
    }
    
    public void removeInvalidEntries(long number) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String query = "delete from invalidentries where cardnum = ?";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setLong(1,number);
        ps.executeUpdate();
    }
    
    public boolean checkPin(long cardnum, int pin) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = "select pin from card where number = ?";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setLong(1, cardnum);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int checkpin = rs.getInt("pin");
            if (pin == checkpin) return true; else return false;
        } else return false;
    }
    
    public void withdraw(long number, int amount) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        while (amount%10 != 0) {
            amount--;
        }
        String query = "select accnum, blocked from card where number=?";
        PreparedStatement ps = getConnection().prepareStatement(query);
        ps.setLong(1, number);
        ResultSet rs = ps.executeQuery();
        long accnum;
        boolean blocked;
        if (rs.next()) {
            accnum = rs.getLong("accnum");
            blocked = rs.getString("blocked").equals("Y")?true:false;
            if (blocked) return;
        } else {
            return;
        }
        query = "select balance from account where number = ?";
        ps = getConnection().prepareStatement(query);
        ps.setLong(1, accnum);
        rs = ps.executeQuery();
        double balance = 0;
        if (rs.next()) {
            balance = rs.getDouble("balance");
        } else {
            return;
        }
        
    }
}
