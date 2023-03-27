package adminpanel;


import javax.swing.*;
import java.awt.*;
import database.MySQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class NewItem extends Admin{
    public NewItem() {
        super.topBanner("New Item");


        JTextField itemName= new JTextField();
        super.inputDecorator(itemName,"Item Name",300);

        JTextField itemPrice= new JTextField();
        super.inputDecorator(itemPrice,"Fare /Day (£)",400);

        JTextField itemLateFee= new JTextField();
        super.inputDecorator(itemLateFee,"Late Fee /Day (£)",500);

        JTextField itemMissing= new JTextField();
        super.inputDecorator(itemMissing,"Missing Cost (£)",600);

        JTextField itemMissingFee= new JTextField();
        super.inputDecorator(itemMissingFee,"Missing Cost (£)",600);

        JButton addItemButton= new JButton("Add Item");
        super.buttonStructure(addItemButton,800,700,300,50);
        addItemButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        String item= itemName.getText().trim();
                        double fare= Double.parseDouble(itemPrice.getText().trim());
                        double lateFee= Double.parseDouble(itemLateFee.getText().trim());
                        double missingFee= Double.parseDouble(itemMissingFee.getText().trim());
                        
                        try {
                            PreparedStatement pstmt = MySQL.connection.prepareStatement("INSERT INTO item (item_name, fare_fee, late_fee, lost_fee) VALUES (?, ?, ?, ?)");
                           pstmt.setString(1, item);
                           pstmt.setDouble(2, fare);
                           pstmt.setDouble(3, lateFee);
                           pstmt.setDouble(4, missingFee);
                            
                            int rowAffected= pstmt.executeUpdate();
                            if(rowAffected>0){
                               JOptionPane.showMessageDialog(null,"Item is added!");
                               itemName.setText("");
                               itemPrice.setText("");
                               itemLateFee.setText("");
                               itemMissingFee.setText("");
                            }
                            
                            pstmt.close();
                            
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });



    }


}
