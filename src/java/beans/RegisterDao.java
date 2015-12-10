/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jhd29_000
 */
public class RegisterDao {
    
        public boolean checkUser(String userID) throws ClassNotFoundException { // ClassNotFoundException was added...
        
        System.out.println("Checking register information...");
                
        boolean status = false;
        Connection db = null; // Connection conn = null;

        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
//            conn = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/tutorarchive", "root", "pass");
                        
            db = beans.Database.getDatabaseConnection();
            
            String query = "select * from users where userID=?";
            PreparedStatement ps = db.prepareStatement(query);
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("userID").equals(userID)) {
                    status = true; // User found
                } else {
                    status = false; // User not found
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
    
        public boolean checkEmail(String email) throws ClassNotFoundException {
        
        System.out.println("Checking email information...");
                
        boolean status = false;
        Connection db = null;

        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
//            conn = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/tutorarchive", "root", "pass");
            
            db = beans.Database.getDatabaseConnection();
            
            String query = "select * from users where email=?";
            PreparedStatement ps = db.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("email").equals(email)) {
                    status = true; // email found
                } else {
                    status = false; // email not found
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
        
        public void insertData(String inputUserReg, String inputPasswordReg, String inputFullName, String radio , String inputEmail) throws ClassNotFoundException {
        System.out.println("Inserting data from registration...");
        String hash = hashPassword(inputPasswordReg);        
        Connection db = null;

        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
//            conn = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/tutorarchive", "root", "pass");
            
            db = beans.Database.getDatabaseConnection();
            
            String query = "INSERT into tutors.users (userID, password, name, tutorSubjects, email) VALUES(?,?,?,?,?)";
            PreparedStatement ps = db.prepareStatement(query);
            ps.setString(1, inputUserReg);
            ps.setString(2, hash);
            ps.setString(3, inputFullName);
            ps.setString(4, radio);
            ps.setString(5, inputEmail);
            ps.executeUpdate();
    } catch(SQLException sql) {
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
       private static String hashPassword(String password) {
       System.out.println("Hashing password...");
       String digest;
       try {
           MessageDigest md = MessageDigest.getInstance("md5");
           md.reset();
           byte[] bytes = md.digest(password.getBytes());
           digest = new BigInteger(1, bytes).toString(16);
       }
       catch (NoSuchAlgorithmException nsae) {
           nsae.printStackTrace();
           digest = null;
       }
       return digest;
  }
}
