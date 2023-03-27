package adminpanel;


import database.Loan;
import database.MySQL;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Home extends Admin {
    public Home() throws SQLException {
        super.topBanner("Home");


/*******************************  Borrower Section   ********************************/


        JLabel approvalLabel= new JLabel("Borrower Information");
        approvalLabel.setBounds(500,200,400,30);
        approvalLabel.setFont(new Font("verdana", Font.BOLD, 30));
        approvalLabel.setForeground(Color.YELLOW);
        panel.add(approvalLabel);

        JTextField idField= new JTextField();
        super.inputDecorator(idField,"User ID",325);


        JButton viewButton= new JButton("View Details");
        super.buttonStructure(viewButton,800,400,300,50);
        
        
        JPanel detail= new JPanel();
        detail.setBounds(400,550,700,400);
        detail.setLayout(null);
        detail.setBackground(Color.DARK_GRAY);
        detail.setVisible(false);
        
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
        
        detail.removeAll();  // Without this line, previous searched result will appear on the screen
                                     

        int borrowerId= Integer.parseInt(idField.getText().trim());
        Loan loan= new Loan(borrowerId);
        
        JLabel borrowerImageLabel= new JLabel(loan.getBorrowerPhoto());
        borrowerImageLabel.setBounds(30,75,200,250);
        detail.add(borrowerImageLabel);

        
        JLabel borrowerNameLabel= new JLabel("Borrower : "+loan.getBorrower());
        borrowerNameLabel.setBounds(150,25,400,25);
        borrowerNameLabel.setFont(new Font("tahoma", Font.BOLD, 25));
        borrowerNameLabel.setForeground(Color.WHITE);
        detail.add(borrowerNameLabel);
        
        JLabel userIDLabel=    new JLabel("User ID                        :   "+borrowerId);
        userIDLabel.setBounds(250,125,400,20);
        userIDLabel.setFont(new Font("tahoma", Font.BOLD, 20));
        userIDLabel.setForeground(Color.WHITE);
        detail.add(userIDLabel);
        
        JLabel totalLoanLabel=   new JLabel("Total Number of Loan :   "+loan.getTotalLoan());
        totalLoanLabel.setBounds(250,175,400,20);
        totalLoanLabel.setFont(new Font("tahoma", Font.BOLD, 20));
        totalLoanLabel.setForeground(Color.WHITE);
        detail.add(totalLoanLabel);
        
        JLabel activeLoanLabel=  new JLabel("Active Loan                  :   "+loan.getActiveLoan());
        activeLoanLabel.setBounds(250,225,400,20);
        activeLoanLabel.setFont(new Font("tahoma", Font.BOLD, 20));
        activeLoanLabel.setForeground(Color.WHITE);
        detail.add(activeLoanLabel);
        
        JLabel repaidLoanLabel=  new JLabel("Repaid Loan                :   "+loan.getRepaidLoan());
        repaidLoanLabel.setBounds(250,275,400,20);
        repaidLoanLabel.setFont(new Font("tahoma", Font.BOLD, 20));
        repaidLoanLabel.setForeground(Color.WHITE);
        detail.add(repaidLoanLabel);
        
        JLabel repaidAmountLabel= new JLabel("Repaid Amount           :   £"+loan.getPaidAmount());
        repaidAmountLabel.setBounds(250,325,400,20);
        repaidAmountLabel.setFont(new Font("tahoma", Font.BOLD, 20));
        repaidAmountLabel.setForeground(Color.WHITE);
        detail.add(repaidAmountLabel);
        
        
                     detail.setVisible(true);
                           // Without following 2 lines, result will show after minimizing the window
                     detail.revalidate();
                     detail.repaint();
        
          
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
        
        panel.add(detail);
        
/******************************** Verification Request ******************************/

        JLabel verifyLabel= new JLabel("Loan Approval Section");     // To be hidden before clicking search button
        verifyLabel.setBounds(1400,170,600,50);
        verifyLabel.setFont(new Font("verdana", Font.BOLD, 30));
        verifyLabel.setForeground(Color.YELLOW);
        panel.add(verifyLabel);


        JPanel verifyPanel= new JPanel();
        BoxLayout layoutVerify = new BoxLayout(verifyPanel, BoxLayout.Y_AXIS);
        verifyPanel.setLayout(layoutVerify);


        
         Statement stmt = MySQL.connection.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT loan_id, user_id, loan.product_code,product_name,product.image,product.available, days FROM loan "
                 + " inner join product on loan.product_code = product.product_code "
                 + " WHERE isapproved = false ");

         while (rs.next()) {
             
            int loan_id = rs.getInt("loan_id");
            int user_id = rs.getInt("user_id");
            int product_code = rs.getInt("product_code");
            int availability = rs.getInt("available");
            int days = rs.getInt("days");
            String product_name = rs.getString("product_name");
            byte[] photo= rs.getBytes("image");

            
            ImageIcon imageIcon= new ImageIcon(photo);
            Image image= imageIcon.getImage();
            Image resizedImage= image.getScaledInstance(100,125, Image.SCALE_SMOOTH);
            ImageIcon productImage= new ImageIcon(resizedImage);
            

                 JPanel cellPanel = new JPanel();
                 cellPanel .setLayout (new GridBagLayout ());
                 GridBagConstraints gbc  = new GridBagConstraints();


                JLabel verifySpace = new JLabel("    ");
                verifySpace .setSize(80,20);


                JLabel labelName = new JLabel(product_name+" ("+days+" Days)");
                labelName .setSize(100,20);
                labelName .setFont(new Font("tahoma", Font.BOLD, 15));

                gbc .insets = new Insets (5, 5, 0, 0);

                JButton buttonImage  = new JButton (productImage);
                buttonImage .setBackground(Color.WHITE);
                gbc .gridx = 0;
                gbc .gridy = 0;
                gbc .gridwidth = 4;
                gbc .gridheight = 4;
                gbc .fill = GridBagConstraints.BOTH;
                cellPanel .add (buttonImage , gbc );

                // Reset to defaults.

                gbc .gridwidth = 1;
                gbc .gridheight = 1;
                gbc .fill = GridBagConstraints.NONE;

                JLabel spaceRight = new JLabel("    ");         // Horizontal Space
                spaceRight .setFont(new Font("tahoma", Font.BOLD, 30));
                gbc .gridx = 4;
                gbc .gridy = 0;
                cellPanel .add (spaceRight , gbc );

                JButton buttonApprove  = new JButton ("Approve");
                buttonApprove .setBackground(Color.ORANGE);
                buttonApprove .setFont(new Font("verdana", Font.PLAIN, 15));
                gbc .gridx = 4;
                gbc .gridy = 1;
                cellPanel .add (buttonApprove , gbc );

                JButton buttonRemove  = new JButton ("Remove");
                buttonRemove .setBackground(Color.RED);
                buttonRemove .setFont(new Font("verdana", Font.PLAIN, 15));
                gbc .gridx = 4;
                gbc .gridy = 2;
                cellPanel .add (buttonRemove , gbc );



                buttonImage.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null,"User ID: "+user_id+"\n"
                                + "Loan ID: "+loan_id);
                    }
                });

                buttonApprove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        PreparedStatement pstmt;
                        try {
                            pstmt = MySQL.connection.prepareStatement("UPDATE loan SET isapproved = ?, approve_date = now() WHERE loan_id = ?");
                        
                            pstmt.setBoolean(1, true);   // Set isverified = true
                            pstmt.setInt(2, loan_id);
                            pstmt.executeUpdate();
                            
                            buttonApprove.setText("Approved");
                            buttonApprove.setBackground(Color.GREEN);
                            buttonRemove.setVisible(false);

                            pstmt.close();
                        
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                buttonRemove.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        try {
                            PreparedStatement pstmt = MySQL.connection.prepareStatement("DELETE FROM loan WHERE loan_id = ?");
                            pstmt.setInt(1, loan_id);
                            pstmt.executeUpdate();
                            
                            
                                PreparedStatement pstmtUPDATE = MySQL.connection.prepareStatement("UPDATE product SET available = ? WHERE product_code = ?");
                                pstmtUPDATE.setInt(1, (availability+1)); 
                                pstmtUPDATE.setInt(2, product_code);

                                pstmtUPDATE.executeUpdate();
                                pstmtUPDATE.close();
                            
                            
                            buttonRemove.setText("Removed");
                            buttonRemove.setBackground(Color.GREEN);
                            buttonApprove.setVisible(false);
                            
                            pstmt.close();
                            
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                verifyPanel.add(verifySpace );
                verifyPanel.add(labelName );
                verifyPanel.add(cellPanel );


            }



                stmt.close();
                rs.close();


        JScrollPane verifyScrollPane = new JScrollPane(verifyPanel);
        //   scrollPane.setBounds(1220,300,680,500);
        verifyScrollPane.setBounds(1300,250,600,700);
       panel.add(verifyScrollPane);
       
       
    }

    
}