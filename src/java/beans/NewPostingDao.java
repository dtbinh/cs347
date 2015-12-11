/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Patrick Winters
 */
public class NewPostingDao 
{
    public void submitPost(int postID, String name, String type, String subject, String message) throws ClassNotFoundException 
    {
        System.out.println("Submitting New Post...");
        
        Connection db = null; // Connection conn = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            db = beans.Database.getDatabaseConnection();
            String query = "insert into tutors.postings (postingID, name, "
                    + "type, timeCreated, timeRequested, msg, status) values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = db.prepareStatement(query);
            System.out.println("Here");
            ps.setInt(1, postID);
            System.out.println("There");
            ps.setString(2, name);
            System.out.println("Everywhere");
            ps.setString(3, type);
            System.out.println("Wild");
            ps.setString(4, "" + System.currentTimeMillis());
            System.out.println("Things");
            ps.setString(5, "today? I guess");
            System.out.println("Test");
            ps.setString(6, subject);
            System.out.println("TTPI");
            ps.setString(7, message);
            System.out.println("Conner");
            ps.setInt(8, 1);
            System.out.println("hi");
            ps.executeUpdate();
            System.out.println("There");
            
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
    }
}
