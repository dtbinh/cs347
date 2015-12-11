package beans;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserDao {

    public boolean checkLogin(String userID, String password) throws ClassNotFoundException {
        System.out.println("Checking login information...");
        String hash = hashPassword(password);
        boolean status = false;
        Connection db = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            db = beans.Database.getDatabaseConnection();
            String query = "select userID, password from users where userID=? and password=?";
            PreparedStatement ps = db.prepareStatement(query);
            ps.setString(1, userID);
            ps.setString(2, hash);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("userID").equals(userID)
                        && rs.getString("password").equals(hash)) {
                    status = true; //User and Password Match
                } else {
                    status = false; //User and Password DO NOT Match
                }
            }
        } catch (SQLException e) {
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
    }

    private static String hashPassword(String password) {
        String digest;
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            md.reset();
            byte[] bytes = md.digest(password.getBytes());
            digest = new BigInteger(1, bytes).toString(16);
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
            digest = null;
        }
        return digest;
    }
    
        public boolean getUsers(String inputUser) throws ClassNotFoundException {
        System.out.println("Checking user information...");
        System.out.println("Input User: " + inputUser);
        boolean status = false;
        Connection db = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            db = beans.Database.getDatabaseConnection();
            String query = "select userID from tutors.users where userID=?";
            PreparedStatement ps = db.prepareStatement(query);
            ps.setString(1, inputUser);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("userID").equals(inputUser)) {
                    System.out.println("User found");
                    status = true; //User 
                } else {
                    System.out.println("User not found");
                    status = false; //User DNE
                }
            }
        } catch (SQLException e) {
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
        }
        
    public void insertData(String fromUser, String inputUser, String messageTitle, String messageBody) throws ClassNotFoundException {
        System.out.println("Inserting data from registration...");
        Connection db = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            db = beans.Database.getDatabaseConnection();
            String query = "INSERT into tutors.messages (from_id, to_id, message_title, message_body) VALUES(?,?,?,?)";
            PreparedStatement ps = db.prepareStatement(query);
            ps.setString(1, fromUser);
            ps.setString(2, inputUser);
            ps.setString(3, messageTitle);
            ps.setString(4, messageBody);
            ps.executeUpdate();
        } catch (SQLException sql) {
        } finally {
            if (db != null) {
                try {
                    db.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
