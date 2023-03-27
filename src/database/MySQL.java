package database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySQL {
   public static Connection connection;


   static {

       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           connection = DriverManager.getConnection("jdbc:mysql://localhost/entertainment_rental_shop","root","");

       } catch (Exception e) {
           e.printStackTrace();
           JOptionPane.showMessageDialog(null, "Connection Failed!\nTry again later.");
       }
   }

}
