package adminpanel;


import javax.swing.*;
import java.awt.*;
import database.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class NewItem extends Admin{
    public NewItem() {
        super.topBanner("New Item");


        JTextField itemNameField= new JTextField();
        super.inputDecorator(itemNameField,"Item Name",300);
        itemNameField.addKeyListener(new KeyAdapter(){
            
            public void keyTyped(KeyEvent e){
                 
                char ch= e.getKeyChar();
               
                if(!(ch >='A' && ch <='Z' || ch >='a' && ch <='z'  ||(ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_DELETE))){
                     e.consume();
                    JOptionPane.showMessageDialog(null, " Enter only alphabets.");
                   
                }
             
            }
        });
        
        

        JTextField itemPriceField= new JTextField();
        super.inputDecorator(itemPriceField,"Fare /Day (£)",400);
        itemPriceField.addKeyListener(new KeyAdapter(){
            
            public void keyTyped(KeyEvent e){
                 
                char ch= e.getKeyChar();
               
                if(!(ch >='0' && ch <='9' || (ch==KeyEvent.VK_PERIOD) ||(ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_DELETE))){
                     e.consume();
                    JOptionPane.showMessageDialog(null, " Enter only numbers.");
                   
                }
             
            }
        });

        JTextField itemLateFeeField= new JTextField();
        super.inputDecorator(itemLateFeeField,"Late Fee /Day (£)",500);
        itemLateFeeField.addKeyListener(new KeyAdapter(){
            
            public void keyTyped(KeyEvent e){
                 
                char ch= e.getKeyChar();
               
                if(!(ch >='0' && ch <='9' || (ch==KeyEvent.VK_PERIOD) ||(ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_DELETE))){
                     e.consume();
                    JOptionPane.showMessageDialog(null, " Enter only numbers.");
                   
                }
             
            }
        });

        JTextField itemMissingFeeField= new JTextField();
        super.inputDecorator(itemMissingFeeField,"Missing Cost (£)",600);
        itemMissingFeeField.addKeyListener(new KeyAdapter(){
            
            public void keyTyped(KeyEvent e){
                 
                char ch= e.getKeyChar();
               
                if(!(ch >='0' && ch <='9' || (ch==KeyEvent.VK_PERIOD) ||(ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_DELETE))){
                     e.consume();
                    JOptionPane.showMessageDialog(null, " Enter only numbers.");
                   
                }
             
            }
        });

        JButton addItemButton= new JButton("Add Item");
        super.buttonStructure(addItemButton,800,700,300,50);
        addItemButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        String item= itemNameField.getText().trim();
                        double fare= Double.parseDouble(itemPriceField.getText().trim());
                        double lateFee= Double.parseDouble(itemLateFeeField.getText().trim());
                        double missingFee= Double.parseDouble(itemMissingFeeField.getText().trim());
                        
                        try {
                            PreparedStatement pstmt = MySQL.connection.prepareStatement("INSERT INTO item (item_name, fare_fee, late_fee, lost_fee) VALUES (?, ?, ?, ?)");
                           pstmt.setString(1, item);
                           pstmt.setDouble(2, fare);
                           pstmt.setDouble(3, lateFee);
                           pstmt.setDouble(4, missingFee);
                            
                            int rowAffected= pstmt.executeUpdate();
                            if(rowAffected>0){
                               JOptionPane.showMessageDialog(null,"Item is added!");
                               itemNameField.setText("");
                               itemPriceField.setText("");
                               itemLateFeeField.setText("");
                               itemMissingFeeField.setText("");
                            }
                            
                            pstmt.close();
                            
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });



    }


}
