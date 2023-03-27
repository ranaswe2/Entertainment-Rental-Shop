package adminpanel;

import database.MySQL;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class UserRequest extends Admin{


    public UserRequest() throws Exception {
        super.topBanner("User Request");
        
        
/******************************** Verification Request ******************************/

        JLabel verifyLabel= new JLabel("Verification Request");     // To be hidden before clicking search button
        verifyLabel.setBounds(460,170,600,50);
        verifyLabel.setFont(new Font("verdana", Font.BOLD, 40));
        verifyLabel.setForeground(Color.YELLOW);
        panel.add(verifyLabel);


        JPanel verifyPanel= new JPanel();
        BoxLayout layoutVerify = new BoxLayout(verifyPanel, BoxLayout.Y_AXIS);
        verifyPanel.setLayout(layoutVerify);


        
         Statement stmt = MySQL.connection.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT user_id, name, photo FROM user WHERE isverified = false");

         while (rs.next()) {
             
            int id = rs.getInt("user_id");
            String name = rs.getString("name");
            byte[] photo= rs.getBytes("photo");

            
            ImageIcon imageIcon= new ImageIcon(photo);
            Image image= imageIcon.getImage();
            Image resizedImage= image.getScaledInstance(100,125, Image.SCALE_SMOOTH);
            ImageIcon profilePicture= new ImageIcon(resizedImage);
            

                 JPanel verifyCellPanel = new JPanel();
                 verifyCellPanel .setLayout (new GridBagLayout ());
                 GridBagConstraints verifyGbc  = new GridBagConstraints();


                JLabel verifySpace = new JLabel("    ");
                verifySpace .setSize(80,20);


                JLabel labelName = new JLabel(name);
                labelName .setSize(80,20);
                labelName .setFont(new Font("tahoma", Font.BOLD, 15));

                verifyGbc .insets = new Insets (5, 5, 0, 0);

                JButton verifyProfileButtonImage  = new JButton (profilePicture);
                verifyProfileButtonImage .setBackground(Color.WHITE);
                verifyGbc .gridx = 0;
                verifyGbc .gridy = 0;
                verifyGbc .gridwidth = 4;
                verifyGbc .gridheight = 4;
                verifyGbc .fill = GridBagConstraints.BOTH;
                verifyCellPanel .add (verifyProfileButtonImage , verifyGbc );

                // Reset to defaults.

                verifyGbc .gridwidth = 1;
                verifyGbc .gridheight = 1;
                verifyGbc .fill = GridBagConstraints.NONE;

                JLabel verifySpaceRight = new JLabel("    ");         // Horizontal Space
                verifySpaceRight .setFont(new Font("tahoma", Font.BOLD, 30));
                verifyGbc .gridx = 4;
                verifyGbc .gridy = 0;
                verifyCellPanel .add (verifySpaceRight , verifyGbc );

                JButton verifyButtonApprove  = new JButton ("Approve");
                verifyButtonApprove .setBackground(Color.ORANGE);
                verifyButtonApprove .setFont(new Font("verdana", Font.PLAIN, 15));
                verifyGbc .gridx = 4;
                verifyGbc .gridy = 1;
                verifyCellPanel .add (verifyButtonApprove , verifyGbc );

                JButton verifyButtonRemove  = new JButton ("Remove");
                verifyButtonRemove .setBackground(Color.RED);
                verifyButtonRemove .setFont(new Font("verdana", Font.PLAIN, 15));
                verifyGbc .gridx = 4;
                verifyGbc .gridy = 2;
                verifyCellPanel .add (verifyButtonRemove , verifyGbc );



                verifyProfileButtonImage .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null,"User ID: "+id);
                    }
                });

                verifyButtonApprove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        PreparedStatement pstmt;
                        try {
                            pstmt = MySQL.connection.prepareStatement("UPDATE user SET isverified = ? WHERE user_id = ?");
                        
                            pstmt.setBoolean(1, true);   // Set isverified = true
                            pstmt.setInt(2, id);
                            pstmt.executeUpdate();
                            
                            verifyButtonApprove.setText("Approved");
                            verifyButtonApprove.setBackground(Color.GREEN);
                            verifyButtonRemove.setVisible(false);

                            pstmt.close();
                        
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                verifyButtonRemove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        try {
                            PreparedStatement pstmt = MySQL.connection.prepareStatement("DELETE FROM user WHERE user_id = ?");
                            pstmt.setInt(1, id);
                            pstmt.executeUpdate();
                            
                            verifyButtonRemove.setText("Removed");
                            verifyButtonRemove.setBackground(Color.GREEN);
                            verifyButtonApprove.setVisible(false);
                            
                            pstmt.close();
                            
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                verifyPanel.add(verifySpace );
                verifyPanel.add(labelName );
                verifyPanel.add(verifyCellPanel );


            }



                stmt.close();
                rs.close();


        JScrollPane verifyScrollPane = new JScrollPane(verifyPanel);
        //   scrollPane.setBounds(1220,300,680,500);
        verifyScrollPane.setBounds(400,250,600,700);
       panel.add(verifyScrollPane);
       
       
       
       
       
/**************************************** Unlock Request ************************************/

        JLabel unlockLabel= new JLabel("Unlock Request");     // To be hidden before clicking search button
        unlockLabel.setBounds(1420,170,600,50);
        unlockLabel.setFont(new Font("verdana", Font.BOLD, 40));
        unlockLabel.setForeground(Color.YELLOW);
        panel.add(unlockLabel);


        JPanel unlockPanel= new JPanel();
        BoxLayout unlockLayout = new BoxLayout(unlockPanel, BoxLayout.Y_AXIS);
        unlockPanel.setLayout(unlockLayout);



         Statement stmtUnlock = MySQL.connection.createStatement();
         ResultSet rsUnlock = stmtUnlock.executeQuery("SELECT user_id, name, photo, isverified FROM user WHERE wronginput=3");

         while (rsUnlock.next()) {
            int id = rsUnlock.getInt("user_id");
            String name = rsUnlock.getString("name");
            byte[] photo= rsUnlock.getBytes("photo");

            
            ImageIcon imageIcon= new ImageIcon(photo);
            Image image= imageIcon.getImage();
            Image resizedImage= image.getScaledInstance(100,125, Image.SCALE_SMOOTH);
            ImageIcon profilePicture= new ImageIcon(resizedImage);
            
            
                 JPanel cellPanelUnlock = new JPanel();
                 cellPanelUnlock .setLayout (new GridBagLayout ());
                 GridBagConstraints gbcUnlock  = new GridBagConstraints();


                JLabel spaceUnlock = new JLabel("    ");
                spaceUnlock .setSize(80,20);


                JLabel labelUnlockName = new JLabel(name);
                labelUnlockName .setSize(80,20);
                labelUnlockName .setFont(new Font("tahoma", Font.BOLD, 15));

                gbcUnlock .insets = new Insets (5, 5, 0, 0);
 
                JButton buttonProfileImageUnlock  = new JButton (profilePicture);
                buttonProfileImageUnlock .setBackground(Color.WHITE);
                gbcUnlock.gridx = 0;
                gbcUnlock.gridy = 0;
                gbcUnlock.gridwidth = 4;
                gbcUnlock.gridheight = 4;
                gbcUnlock.fill = GridBagConstraints.BOTH;
                cellPanelUnlock .add (buttonProfileImageUnlock, gbcUnlock );

                // Reset to defaults.

                gbcUnlock.gridwidth = 1;
                gbcUnlock.gridheight = 1;
                gbcUnlock.fill = GridBagConstraints.NONE;

                JLabel spaceRightUnlock= new JLabel("    ");         // Horizontal Space
                spaceRightUnlock.setFont(new Font("tahoma", Font.BOLD, 30));
                gbcUnlock .gridx = 4;
                gbcUnlock .gridy = 0;
                cellPanelUnlock .add (spaceRightUnlock , gbcUnlock );


                JButton buttonUnlock = new JButton ("Unlock");
                buttonUnlock .setBackground(Color.ORANGE);
                buttonUnlock .setFont(new Font("verdana", Font.PLAIN, 15));
                gbcUnlock .gridx = 4;
                gbcUnlock .gridy = 1;
                cellPanelUnlock .add (buttonUnlock , gbcUnlock );






                buttonUnlock .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        PreparedStatement pstmt;
                        try {
                            pstmt = MySQL.connection.prepareStatement("UPDATE user SET wronginput = ? WHERE user_id = ?");
                        
                            pstmt.setInt(1, 0);   // Set wronginput=0
                            pstmt.setInt(2, id);
                            pstmt.executeUpdate();
                            
                            buttonUnlock.setText("Unlocked");
                            buttonUnlock.setBackground(Color.GREEN);

                            pstmt.close();
                        
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                });

                unlockPanel.add(spaceUnlock );
                unlockPanel.add(labelUnlockName );
                unlockPanel.add(cellPanelUnlock );

            }

         rsUnlock.close();
         stmtUnlock.close();
        // con.close();

        JScrollPane scrollPane = new JScrollPane(unlockPanel);
        //   scrollPane.setBounds(1220,300,680,500);
        scrollPane.setBounds(1300,250,600,700);
       panel.add(scrollPane);
        
    }
}

