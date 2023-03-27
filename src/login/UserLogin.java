package login;

import database.MySQL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import userpanel.Home;

public class UserLogin implements Login{
     private JFrame frame;
     private static int id;
     private static String name;
     private static String username;
     private static String password;
     private static byte []photo;
     boolean isverified;
     int wronginput;
     int count=0;
    
    
    public UserLogin(String username, String password,JFrame frame) {
        this.username = username;
        this.password = password;
        this.frame = frame;
    }
    
    public UserLogin(){}

    @Override
    public void login() throws SQLException {

            
         Statement stmt = MySQL.connection.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM user where username='"+username+"'");

         if(rs.next()) {
            id = rs.getInt("user_id");
            name = rs.getString("name");
            String pass = rs.getString("password");
            photo=rs.getBytes("photo");
            isverified=rs.getBoolean("isverified");
            wronginput=rs.getInt("wronginput");
            
            
            if(pass.equals(password) && isverified && wronginput<3){
                
                new Home();
                frame.dispose();
                
                
         PreparedStatement pstmt = MySQL.connection.prepareStatement("UPDATE user SET wronginput = ? WHERE username = ?");
         pstmt.setInt(1, 0);
         pstmt.setString(2, username);
         pstmt.executeUpdate();
         pstmt.close();
                
            }
            else if(wronginput==3){
                JOptionPane.showMessageDialog(null, "Your account has locked.\n"
                                + "You need an admin verification to unlock the account.\n "
                                + "Hence, you have to visit the shop and meet with an admin.");
            } 
            else if( !isverified ){
                JOptionPane.showMessageDialog(null, "Your Information has saved.\n"
                                + "Your account has not approved by an admin till now.\n "
                                + "Hence, you have to visit the shop and meet with an admin.");
            }
            else{
                count= wronginput+1;
                
         PreparedStatement pstmt = MySQL.connection.prepareStatement("UPDATE user SET wronginput = ? WHERE username = ?");
         pstmt.setInt(1, count);
         pstmt.setString(2, username);
         pstmt.executeUpdate();
         pstmt.close();
         
                if(count==3){
                       JOptionPane.showMessageDialog(null," Your account is locked!");
                
                }
                else{
                       JOptionPane.showMessageDialog(null,"Enter Correct Username and Password! You have "+(3-count)+" more chance.");
                 }
            }
            
                
        }

         rs.close();
         stmt.close();
         

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getPhoto() {
        return photo;
    }

}
