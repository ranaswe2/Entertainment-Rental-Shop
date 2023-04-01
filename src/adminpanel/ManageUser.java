package adminpanel;

import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import database.MySQL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import start.CombinedFrame;

public class ManageUser extends Admin{
    public ManageUser() {
        super.topBanner("Manage User");


/*******************************   Reset Password   ********************************/


        JLabel resetPasswordLabel= new JLabel("Reset Password");
        resetPasswordLabel.setBounds(600,200,400,30);
        resetPasswordLabel.setFont(new Font("tahoma", Font.BOLD, 30));
        resetPasswordLabel.setForeground(Color.MAGENTA);
        panel.add(resetPasswordLabel);


        String userList[] = { "User", "Admin"};

        JLabel select= new JLabel("Select");
        select.setBounds(400,300,400,30);
        select.setFont(new Font("verdana", Font.BOLD, 30));
        select.setForeground(Color.WHITE);
        panel.add(select);

        JComboBox item= new JComboBox(userList);
        item.setBounds(800,300,300,50);
        item.setFont(new Font("tahoma", Font.BOLD, 30));
        panel.add(item);

        JTextField idField= new JTextField();
        super.inputDecorator(idField,"User ID",400);

        JTextField passwordField= new JTextField();
        super.inputDecorator(passwordField,"New Password",500);

        JButton changePasswordButton= new JButton("Update");
        super.buttonStructure(changePasswordButton,800,600,300,50);
        
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        String table= item.getSelectedItem().toString().toLowerCase();
                        int id= Integer.parseInt(idField.getText().trim());
                        String password= passwordField.getText();
                        
                        PreparedStatement pstmt;
                        try {
                            pstmt = MySQL.connection.prepareStatement("UPDATE "+table+" SET password = ? WHERE user_id = ?");
                        
                            pstmt.setString(1, password);   // Set isverified = true
                            pstmt.setInt(2, id);
                            
                            int rowAffected= pstmt.executeUpdate();
                            if(rowAffected>0){
                               JOptionPane.showMessageDialog(null,"Password of User ID: "+id+" is changed!");
                               idField.setText("");
                               passwordField.setText("");
                            }
                            pstmt.close();
                            
                        
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
            }
        });


        JButton viewDetailsButton = new JButton("View Admin Details");
        viewDetailsButton.setBounds(450, 750, 500,  40);
        viewDetailsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        viewDetailsButton.setFont(new Font("Serif", Font.BOLD, 24));
        viewDetailsButton.setBackground(Color.ORANGE);

        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminDetailsWindow();
            }
        
        });
        
        
        
        panel.add(viewDetailsButton);
        
        
/*******************************   Delete & Lock Account   ********************************/


        JLabel danger= new JLabel("Danger Zone");
        danger.setBounds(1400,280,250,30);
        danger.setFont(new Font("verdana", Font.BOLD, 30));
        danger.setForeground(Color.RED);
        panel.add(danger);


        JLabel idLabel= new JLabel("User ID");
        idLabel.setBounds(1250,400,300,30);
        idLabel.setFont(new Font("verdana", Font.BOLD, 30));
        idLabel.setForeground(Color.WHITE);
        panel.add(idLabel);

        JTextField idToDeleteField= new JTextField();
        idToDeleteField.setBounds(1550,400,300,50);
        idToDeleteField.setFont(new Font("tahoma", Font.BOLD, 30));
        panel.add(idToDeleteField);


        JButton deleteAccountButton= new JButton("Delete Account");
        deleteAccountButton.setBackground(Color.RED);
        deleteAccountButton.setBounds(1550,500,300,50);
        deleteAccountButton.setFont(new Font("tahoma", Font.BOLD, 30));
        
        deleteAccountButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        int id= Integer.parseInt(idToDeleteField.getText().trim());
                        
                        try {
                            PreparedStatement pstmt = MySQL.connection.prepareStatement("DELETE FROM user WHERE user_id = ?");
                            pstmt.setInt(1, id);
                            
                            int rowAffected= pstmt.executeUpdate();
                            if(rowAffected>0){
                               JOptionPane.showMessageDialog(null,"Account of User ID: "+id+" is removed!");
                               idToDeleteField.setText("");
                            }
                            pstmt.close();
                            
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
        
        
        
        panel.add(deleteAccountButton);
    }



    public void paint(Graphics gp) {

        super.paint(gp);
        gp.setColor(Color.RED);
        Graphics2D graphics = (Graphics2D) gp;

        Line2D rectangleTop = new Line2D.Float(1220, 300, 1900, 300);
        graphics.draw(rectangleTop);
        Line2D rectangleLeft = new Line2D.Float(1220, 300, 1220, 700);
        graphics.draw(rectangleLeft);
        Line2D rectangleRight = new Line2D.Float(1900, 300, 1900, 700);
        graphics.draw(rectangleRight);
        Line2D rectangleBottom = new Line2D.Float(1220, 700, 1900, 700);
        graphics.draw(rectangleBottom);

    }
}

