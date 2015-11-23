package beans;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public boolean checkLogin(String userID, String password){
        
        System.out.println("Checking login information...");
        
        String hash = hashPassword(password);
        boolean status = false;
        Connection conn = null;
//        HttpSession session = ServletActionContext.getRequest().getSession(false);
//        session.setMaxInactiveInterval(20*60);
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tutorarchive", "root", "pass");
            String query = "select userID, password from users where userID=? and password=?";
            PreparedStatement ps = conn.prepareStatement(query);
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
            if (conn != null) {
                try {
                    conn.close();
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
       }
       catch (NoSuchAlgorithmException nsae) {
           nsae.printStackTrace();
           digest = null;
       }
       return digest;
  }
}



















//package beans;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class UserDao {
//
//    public boolean checkLogin(String userID, String password) {
//        boolean status = false;
//        Connection conn = null;
//        try {
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            conn = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/tutorarchive", "root", "pass");
//            String query = "select userID, password from users where userID=? and password=?";
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setString(1, userID);
//            ps.setString(2, password);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                if (rs.getString("userID").equals(userID)
//                        && rs.getString("password").equals(password)) {
//                    status = true;
//                } else {
//                    status = false;
//                }
//            }
//        } catch (SQLException e) {
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return status;
//    }  
//}
