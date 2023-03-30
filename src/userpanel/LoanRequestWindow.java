package userpanel;

import database.ItemPrice;
import database.Loan;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.sql.SQLException;
import login.UserLogin;


public class LoanRequestWindow{

    private JLabel topBanner,usernameLabel;
    private JFrame frame;
    private JButton requestButton;
    private JPanel panel;
    private JTextField dayField;
    private Container container;
    //setLayout(new GridBagLayout());





    public LoanRequestWindow(String itemName, int productCode) {
        
        frame= new JFrame("Entertainment Rental Shop");
        container= frame.getContentPane();
        frame.setLayout(null);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#051E3E"));

        topBanner= new JLabel();
        usernameLabel= new JLabel();

        dayField = new JTextField(24);
        dayField.addKeyListener(new KeyAdapter(){
            
            public void keyTyped(KeyEvent e){
                 
                char ch= e.getKeyChar();
               
                if(!(ch >='0' && ch <='9' || (ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_DELETE))){
                     e.consume();
                    JOptionPane.showMessageDialog(null, " Enter only numbers.");
                   
                }
             
            }
        });

        requestButton = new JButton("Loan Request");
        requestButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        requestButton.setFont(new Font("Serif", Font.BOLD, 24));
        requestButton.setBackground(Color.ORANGE);

        requestButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int days= Integer.parseInt(dayField.getText().trim());
                        
                        try {
                            
                            ItemPrice price= new ItemPrice(itemName);
                            
                            int result = JOptionPane.showConfirmDialog(null,"Fair Price /Day: "+price.getFairPrice()
                                    + "\nEstimated Fair Price (P)"+price.getTotalFairPrice(days)
                                    + "\nLate Fee /Day: "+price.getLateFee()
                                    + "\nMissing Fee: "+price.getMissingFee()
                                    + "\n"
                                    + "Are you sure?\n"
                                    + "Do you want to take a loan?", 
                                    "Loan Confirmation",
                                     JOptionPane.YES_NO_OPTION,
                                     JOptionPane.QUESTION_MESSAGE);
                            
                            if(result == JOptionPane.YES_OPTION){ 
                                
                                UserLogin login= new UserLogin();
                                Loan lone= new Loan();
                                
                                lone.sendLoanRequest(login.getId(), productCode, days);
                                
                                frame.dispose();
                            }
                            else{
                                frame.dispose();
                            }

                        
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                });
        

        // GridBagConstraints g = new GridBagConstraints();





        topBanner.setText("Loan Request Form");
        topBanner.setBounds(80, 50, 500,  40);
        topBanner.setFont(new Font("Serif", Font.BOLD, 44));
        topBanner.setForeground(Color.WHITE);


        usernameLabel.setText("Loaning Period (Days) ");
        usernameLabel.setBounds(50, 250, 200,  40);
        usernameLabel.setForeground(Color.WHITE);


        dayField.setText("");
        dayField.setEditable(true);
        dayField.setBounds(300, 250, 200,  40);
        dayField.setFont(new Font("Serif", Font.PLAIN, 18));



        requestButton.setBounds(300, 350, 200,  40);

        
        JButton cancelButton = new JButton("Go Back");
        cancelButton.setBounds(300, 550, 200,  40);
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.setFont(new Font("Serif", Font.BOLD, 24));
        cancelButton.setBackground(Color.RED);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        
        });
        
        panel.add(topBanner);
        panel.add(usernameLabel);

        panel.add(dayField);
        panel.add(requestButton);
        panel.add(cancelButton);
        panel.setSize(600,700);
        container.add(panel);

        frame.setSize(600,700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


}