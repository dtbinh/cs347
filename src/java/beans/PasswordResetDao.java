/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PasswordResetDao {

    String personName;
    String email;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public boolean checkEmail(String inputEmail) {

        System.out.println("Checking email information...");

//        String hash = hashPassword(password);
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
            String query = "select email,name from users where email=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, inputEmail);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("email").equals(inputEmail)) {
                    personName = rs.getString("name"); 
                    email=inputEmail;  
                    System.out.println("The persons name from prdao is: " + personName);// I CHANGED THIS!!!
                    status = true; //Email is found.
                } else {
                    status = false; //Email was not found.
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

    public void setToken(String email, String token, String time) {

        System.out.println("Adding token field to table...");
        String hash = hashPassword(token);
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
            String query = "UPDATE users SET token=?, tokenCreate=? WHERE email=?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, token);
            ps.setString(2, time);
            ps.setString(3, email);
            ps.executeUpdate();

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

    } 
    
    public boolean checkToken(String token, String nowTime, String pass)
            throws ParseException, MalformedURLException, UnsupportedEncodingException {

        System.out.println("Checking token information...");
        boolean status = false;
        Connection conn = null;

        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tutorarchive", "root", "pass");
            String query = "select token, tokenCreate from users where token=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("token").equals(token)) {
                    System.out.println("Token was found!");
                    String tokenCreate = rs.getString("tokenCreate");
                    DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Calendar previous = Calendar.getInstance();
                    previous.setTime(df.parse(tokenCreate));
                    Calendar now = Calendar.getInstance();
                    now.setTime(df.parse(nowTime));
                    long diff = now.getTimeInMillis() - previous.getTimeInMillis();
                    if (diff >= 1 * 60 * 1000) {
                        conn = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/tutorarchive", "root", "pass");
                        String del_query = "UPDATE users SET token=?, tokenCreate=? WHERE token=?";
                        PreparedStatement nps = conn.prepareStatement(del_query);
                        nps.setString(1, null);
                        nps.setString(2, null);
                        nps.setString(3, token);
                        nps.executeUpdate();
                        System.out.println("The Token has expired. Deleting token...");
                    } else {
                        String updatePass = "UPDATE users SET password=? WHERE token=?";
                        PreparedStatement nps2 = conn.prepareStatement(updatePass);
                        String hash = hashPassword(pass);
                        nps2.setString(1, hash);
                        nps2.setString(2, token);
                        nps2.executeUpdate();
                        resetToken(token);
                        status = true; // Changed Password.
                    }
                } else {
                    System.out.println("The token was not found!");
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
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
            digest = null;
        }
        return digest;
    }
    
    private void resetToken(String token) throws MalformedURLException, UnsupportedEncodingException {
        System.out.println("Resetting Token...");
        boolean status = false;
        Connection conn = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tutorarchive", "root", "pass");
            String delQuery = "UPDATE users SET token=?, tokenCreate=? WHERE token=?";
            
            PreparedStatement ps = conn.prepareStatement(delQuery);
            ps.setString(1, null);
            ps.setString(2, null);
            ps.setString(3, token);
            ps.executeUpdate();
//            sendEmailSuccess(personName,email);

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
        

    }
    
        
//        public void sendEmailSuccess(String personName, String inputEmail) throws MalformedURLException, UnsupportedEncodingException {
//        final String username = "tutors.jmu@gmail.com";
//        final String password = "tutors@jmu";
////        URL myURL = new URL("http://localhost:8080/TutorsJMU/contact");
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        
//        System.out.println("The persons name is :" + personName);
//        System.out.println("The email is :" + inputEmail);
//        Session session1 = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//        try {
//            Message message = new MimeMessage(session1);
//            message.setFrom(new InternetAddress("tutors.jmu@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(inputEmail));
//            message.setSubject("Tutors@JMU Password Reset");
//            message.setText("Dear " + personName + ","
//                    + "\n\nYour password has been successfully updated."
//                    + "\n\n-------------------------------------------------------------------------------- "
//                    + "\n\nIf you did not request to reset your password or believe you're receiving this email in error,"
//                    + " please contact for assistance."
//                    + "\n\nThank you for choosing us,"
//                    + "\nThe Tutors@JMU Team"
//                    + "\n\nNotice: The information and attachment(s) contained in this communication are intended for the addressee only,"
//                    + " and may be confidential and/or legally privileged. "
//                    + "If you have received this communication in error, "
//                    + "please contact the sender immediately, and delete this communication from any computer or network system. "
//                    + "Any interception, review, printing, copying, re-transmission, dissemination, "
//                    + "or other use of, or taking of any action upon this information by persons or entities other than the "
//                    + "intended recipient is strictly prohibited by law and may subject them to criminal or civil liability. "
//                    + "Tutors@JMU shall not be liable for the improper and/or incomplete transmission of the "
//                    + "information contained in this communication or for any delay in its receipt.");
//
//            Transport.send(message);
//            System.out.println("Success Email Sent");
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
