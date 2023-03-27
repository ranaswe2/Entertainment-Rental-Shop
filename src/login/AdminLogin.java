package login;

import adminpanel.Home;
import database.MySQL;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AdminLogin implements Login{
    JFrame frame;
    String username;
    String password;

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
                new Home();
                frame.dispose();
            }
            else{
                JOptionPane.showMessageDialog(null,"Enter Your Correct Username and Password! ");
            }
    }
}
