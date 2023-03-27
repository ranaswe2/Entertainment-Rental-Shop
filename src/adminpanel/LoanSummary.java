package adminpanel;

import database.Item;

import database.ItemPrice;
import database.MySQL;
import database.ReturnLoan;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import report.ItemReport;
import report.MonthlyReport;

public class LoanSummary extends Admin{
    
    double payment;
    int loanId;
    String flag;
    
    public LoanSummary() throws SQLException {
        
        super.topBanner("Loan Summary");
        
/*******************************   Loan Table   ********************************/
        
        Statement stmt = MySQL.connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT loan_id, user_id, product_code, approve_date, days, return_date, payment, isapproved FROM loan ");
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Loan ID");
        model.addColumn("User ID");
        model.addColumn("Product Code");
        model.addColumn("Days");
        model.addColumn("Approved");
        model.addColumn("Paid(£)");

while(rs.next()) {
    Object[] row = new Object[6];
    row[0] = rs.getInt("loan_id");
    row[1] = rs.getInt("user_id");
    row[2] = rs.getInt("product_code");
    row[3] = rs.getInt("days");
    row[4] = rs.getBoolean("isapproved");
    row[5] = rs.getDouble("payment");
    model.addRow(row);
}
        JTable table = new JTable(model);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(400,200,600,300);
        scrollPane.setBackground(Color.decode("#051E3E"));
        panel.add(scrollPane);
        
        

/************************** Report ************************************/

        Item list= new Item();
        String itemList[] = list.getItemList();

        JLabel reportLabel= new JLabel("Download Report");
        reportLabel.setBounds(500,550,400,40);
        reportLabel.setFont(new Font("verdana", Font.BOLD, 40));
        reportLabel.setForeground(Color.GREEN);
        panel.add(reportLabel);
        
        
        
        JButton monthlyReportButton= new JButton("Monthly Report");
        super.buttonStructure(monthlyReportButton,400,650,300,50);
        
        monthlyReportButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                   
        String path="";
        JFileChooser j= new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x= j.showSaveDialog(null);
        
        if(x== JFileChooser.APPROVE_OPTION){
            path= j.getSelectedFile().getPath();
        }
        
                        try {
                            MonthlyReport report= new MonthlyReport(path);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        
                    }});
        
        
        JLabel selectLabel= new JLabel("Select Item");
        selectLabel.setVisible(false);
        selectLabel.setBounds(400,750,400,30);
        selectLabel.setFont(new Font("verdana", Font.BOLD, 25));
        selectLabel.setForeground(Color.WHITE);
        panel.add(selectLabel);
        
        JButton itemReportButton= new JButton("Item Report");
        super.buttonStructure(itemReportButton,800,650,300,50);
        
        itemReportButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
        

        selectLabel.setVisible(true);
        
        JComboBox item= new JComboBox(itemList);
        item.setBounds(800,750,300,50);
        item.setFont(new Font("tahoma", Font.BOLD, 25));
        panel.add(item);
        
        
        JButton itemReportDownloadButton= new JButton("Download");
        buttonStructure(itemReportDownloadButton,800,825,300,50);
        
        itemReportDownloadButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                
        String path="";
        JFileChooser j= new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x= j.showSaveDialog(null);
        
        if(x== JFileChooser.APPROVE_OPTION){
            path= j.getSelectedFile().getPath();
        }
                        
                        try {
                            
                            new ItemReport(path, item.getSelectedItem().toString());
                            
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                            }});
                    }});
/*******************************   Receive   ********************************/


        JLabel danger= new JLabel("Receive Product");
        danger.setBounds(1400,200,300,30);
        danger.setFont(new Font("verdana", Font.BOLD, 30));
        danger.setForeground(Color.YELLOW);
        panel.add(danger);


        JLabel loanReturnLabel= new JLabel("Loan ID");
        loanReturnLabel.setBounds(1250,300,300,30);
        loanReturnLabel.setFont(new Font("verdana", Font.BOLD, 30));
        loanReturnLabel.setForeground(Color.WHITE);
        panel.add(loanReturnLabel);

        JTextField loanIdField= new JTextField();
        loanIdField.setBounds(1550,300,300,50);
        loanIdField.setFont(new Font("tahoma", Font.BOLD, 30));
        panel.add(loanIdField);


        JLabel selectItem= new JLabel("Payment Type");
        selectItem.setBounds(1250,425,300,30);
        selectItem.setFont(new Font("verdana", Font.BOLD, 30));
        selectItem.setForeground(Color.WHITE);
        panel.add(selectItem);

        JRadioButton returnButton = new JRadioButton("Return");
        returnButton.setBounds(1550,400,300,50);
        returnButton.setFont(new Font("verdana", Font.BOLD, 30));

        JRadioButton missingButton = new JRadioButton("Missing");
        missingButton.setBounds(1550,450,300,50);
        missingButton.setFont(new Font("verdana", Font.BOLD, 30));

        panel.add(returnButton);
        panel.add(missingButton);

        // Create a button group and add the radio buttons to it
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(returnButton);
        buttonGroup.add(missingButton);


        JLabel paymentLabel= new JLabel("Amount (£)");
        
        JButton checkAmountButton= new JButton("Check Amount");
        super.buttonStructure(checkAmountButton,1550,550,300,50);
        
        checkAmountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                loanId= Integer.parseInt(loanIdField.getText().trim());
                
                try {
                    ReturnLoan loan= new ReturnLoan(loanId);
                    ItemPrice price= new ItemPrice(loan.getItemName());
                    
                    
                    if(returnButton.isSelected()){
                        payment= loan.getDays()*price.getFairPrice() + loan.getLateDays()*price.getLateFee();
                        flag= "Return";
                    }
                    else if(missingButton.isSelected()){
                        payment= price.getMissingFee();
                        flag= "Missing";
                    }
                    
                    
                    

        paymentLabel.setVisible(true);
        
        JTextField paymentField= new JTextField(String.valueOf(payment));
        paymentField.setBounds(1550,700,300,50);
        paymentField.setEditable(false);
        paymentField.setFont(new Font("tahoma", Font.BOLD, 30));
        panel.add(paymentField);


        JButton ReceiveButton= new JButton("Receive");
        ReceiveButton.setBackground(Color.ORANGE);
        ReceiveButton.setBounds(1550,800,300,50);
        ReceiveButton.setFont(new Font("tahoma", Font.BOLD, 30));
          
        
        ReceiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    loan.receiveLoan(loanId, payment, flag);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                    
            }
        
        });
                    
        panel.add(ReceiveButton); 
        
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
                
            }
        
        });
        
        
                    
        paymentLabel.setVisible(false);
        paymentLabel.setBounds(1250,700,300,30);
        paymentLabel.setFont(new Font("verdana", Font.BOLD, 30));
        paymentLabel.setForeground(Color.WHITE);
        panel.add(paymentLabel);
        
        
        
    }
}


