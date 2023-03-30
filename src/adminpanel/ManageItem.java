package adminpanel;

import database.Item;
import database.MySQL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.sql.SQLException;

public class ManageItem extends Admin{
    public ManageItem() throws SQLException {
        super.topBanner("Manage Item");


        Item list= new Item();
        String itemList[] = list.getItemList();

/*******************************   Update Item   ********************************/

        JLabel select= new JLabel("Select Item");
        select.setBounds(400,300,400,30);
        select.setFont(new Font("verdana", Font.BOLD, 30));
        select.setForeground(Color.WHITE);
        panel.add(select);

        JComboBox item= new JComboBox(itemList);
        item.setBounds(800,300,300,50);
        item.setFont(new Font("tahoma", Font.BOLD, 30));
        panel.add(item);

        JTextField itemPrice= new JTextField();
        super.inputDecorator(itemPrice,"Fare /Day (£)",400);
        itemPrice.addKeyListener(new KeyAdapter(){
            
            public void keyTyped(KeyEvent e){
                 
                char ch= e.getKeyChar();
               
                if(!(ch >='0' && ch <='9' || (ch==KeyEvent.VK_PERIOD) ||(ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_DELETE))){
                     e.consume();
                    JOptionPane.showMessageDialog(null, " Enter only numbers.");
                   
                }
             
            }
        });

        JTextField itemLateFee= new JTextField();
        super.inputDecorator(itemLateFee,"Late Fee /Day (£)",500);
        itemLateFee.addKeyListener(new KeyAdapter(){
            
            public void keyTyped(KeyEvent e){
                 
                char ch= e.getKeyChar();
               
                if(!(ch >='0' && ch <='9' || (ch==KeyEvent.VK_PERIOD) ||(ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_DELETE))){
                     e.consume();
                    JOptionPane.showMessageDialog(null, " Enter only numbers.");
                   
                }
             
            }
        });

        JTextField itemMissing= new JTextField();
        super.inputDecorator(itemMissing,"Missing Cost (£)",600);
        itemMissing.addKeyListener(new KeyAdapter(){
            
            public void keyTyped(KeyEvent e){
                 
                char ch= e.getKeyChar();
               
                if(!(ch >='0' && ch <='9' || (ch==KeyEvent.VK_PERIOD) ||(ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_DELETE))){
                     e.consume();
                    JOptionPane.showMessageDialog(null, " Enter only numbers.");
                   
                }
             
            }
        });

        JButton updateItemButton= new JButton("Update Item");
        super.buttonStructure(updateItemButton,800,700,300,50);
        updateItemButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        String itemName= item.getSelectedItem().toString();
                        double fare= Double.parseDouble(itemPrice.getText().trim());
                        double lateFee= Double.parseDouble(itemLateFee.getText().trim());
                        double missingFee= Double.parseDouble(itemMissing.getText().trim());

                        try {
                            list.update(itemName, fare, lateFee, missingFee);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                               itemPrice.setText("");
                               itemLateFee.setText("");
                               itemMissing.setText("");
                               
                    }
        });
/*******************************   Delete Item   ********************************/

        JLabel danger= new JLabel("Danger Zone");
        danger.setBounds(1400,280,250,30);
        danger.setFont(new Font("verdana", Font.BOLD, 30));
        danger.setForeground(Color.RED);
        panel.add(danger);


        JLabel selectItem= new JLabel("Select Item");
        selectItem.setBounds(1250,380,300,30);
        selectItem.setFont(new Font("verdana", Font.BOLD, 30));
        selectItem.setForeground(Color.WHITE);
        panel.add(selectItem);

        JComboBox itemToDelete= new JComboBox(itemList);
        itemToDelete.setBounds(1550,380,300,50);
        itemToDelete.setFont(new Font("tahoma", Font.BOLD, 30));
        panel.add(itemToDelete);


        JButton deleteItem= new JButton("Delete Item");
        deleteItem.setBackground(Color.RED);
        deleteItem.setBounds(1550,480,300,50);
        deleteItem.setFont(new Font("tahoma", Font.BOLD, 30));
        panel.add(deleteItem);

        deleteItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String itemDelete= itemToDelete.getSelectedItem().toString();
                        try {
                            int result = JOptionPane.showConfirmDialog(null,"Are you sure?\n "
                                    + "Do you want to Remove "+itemDelete+" from the list?", 
                                    "Remove Item",
                                     JOptionPane.YES_NO_OPTION,
                                     JOptionPane.QUESTION_MESSAGE);
                            
                            if(result == JOptionPane.YES_OPTION){ 
                                
                                list.delete(itemDelete);
                                JOptionPane.showMessageDialog(null, "Item is removed from the list.");
                                
                            }else if (result == JOptionPane.NO_OPTION){
                                System.out.println("Item is not removed.");
                            }

                        
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                });
        
        
        
        JLabel instr= new JLabel("** If you delete an item, all products of the item category will remove from the system Database.");
        instr.setBounds(1250,600,600,30);
        instr.setForeground(Color.WHITE);
        panel.add(instr);


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

