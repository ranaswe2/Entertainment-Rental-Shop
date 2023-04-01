package login;

import adminpanel.Home;
import database.MySQL;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AdminLogin implements Login{
    private static JFrame frame;
    
     private static int id;
     private static String name;
     private static String username;
    private static String password;

    public AdminLogin(String username, String password,JFrame frame) {
        this.username = username;
        this.password = password;
        this.frame = frame;
    }

    @Override
    public void login() throws SQLException {


            String sql = "SELECT * FROM admin where username='"+username+"' and password='"+password+"'";

            Statement statement = MySQL.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);    // Execute the query

            if(resultSet.next()) {         // Process the result set
                
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
                
                
                new Home();
                frame.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null,"Enter Your Correct Username and Password! ");
            }
    }

    public static int getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static String getUsername() {
        return username;
    }
    
    
    
}
