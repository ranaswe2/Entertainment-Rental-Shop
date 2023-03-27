package userpanel;

import database.MySQL;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import login.UserLogin;

public class LoanedItem  extends User{
    public LoanedItem() throws SQLException {
        
        super.topBanner("Loan History");
        UserLogin login= new UserLogin(); 
        
/*******************************   Loan Table   ********************************/
        
                 JLabel repaidLoanLabel= new JLabel("Repaid Loan");     // To be hidden before clicking search button
                 repaidLoanLabel.setBounds(500,200,400,50);
                 repaidLoanLabel.setFont(new Font("verdana", Font.BOLD, 50));
                 repaidLoanLabel.setForeground(Color.YELLOW);
                 panel.add(repaidLoanLabel);
                 
                 
        Statement stmt = MySQL.connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT loan_id, product_code, approve_date, return_date, payment"
                + " FROM loan where user_id="+login.getId()+" and payment > 0");
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Loan ID");
        model.addColumn("Product Code");
        model.addColumn("Approve Date");
        model.addColumn("Return Date");
        model.addColumn("Paid(£)");

while(rs.next()) {
    Object[] row = new Object[5];
    row[0] = rs.getInt("loan_id");
    row[1] = rs.getInt("product_code");
    row[2] = rs.getString("approve_date");
    row[3] = rs.getString("return_date");
    row[4] = rs.getDouble("payment");
    model.addRow(row);
}
        JTable table = new JTable(model);
        
        JScrollPane scrollPaneTable = new JScrollPane(table);
        scrollPaneTable.setBounds(400,300,600,600);
        scrollPaneTable.setBackground(Color.decode("#051E3E"));
        panel.add(scrollPaneTable);
        
/*  Paid Loan
        
SELECT loan.product_code,product_name,item_name, details, stock, image FROM loan 
join product on loan.product_code = product.product_code 
where user_id= 14364 and payment = 0.0 
*/

/****************************************       Active Loan        ************************************/

                 JLabel resultLabel= new JLabel("Active Loan");     // To be hidden before clicking search button
                 resultLabel.setBounds(1480,200,400,50);
                 resultLabel.setFont(new Font("verdana", Font.BOLD, 50));
                 resultLabel.setForeground(Color.YELLOW);
                 panel.add(resultLabel);
                 
                 
                 JPanel resultPanel= new JPanel();
                 BoxLayout layout = new BoxLayout(resultPanel, BoxLayout.Y_AXIS);
                 resultPanel.setLayout(layout);
                 
                 
             
             try {
                 
                 
                 Statement stmtProduct = MySQL.connection.createStatement();
                 ResultSet rsProduct = stmtProduct.executeQuery("SELECT loan.product_code,product_name,loan_id,details, image,"
                         + " DATE_ADD(loan.approve_date, INTERVAL loan.days DAY) AS returndate FROM loan"
                         + " join product on loan.product_code = product.product_code "
                         + " where user_id="+login.getId()+" and payment IS NULL"); //+" and return_date = null"
                 
                 while (rsProduct.next()) {
                     int product_code = rsProduct.getInt("product_code");
                     int loan_id = rsProduct.getInt("loan_id");
                     String product_name = rsProduct.getString("product_name");
                     String returndate = rsProduct.getString("returndate");
                     String details = rsProduct.getString("details");
                     byte[] photo= rsProduct.getBytes("image");
                     
                     
                     ImageIcon imageIcon= new ImageIcon(photo);
                     Image image= imageIcon.getImage();
                     Image resizedImage= image.getScaledInstance(100,125, Image.SCALE_SMOOTH);
                     ImageIcon productPicture= new ImageIcon(resizedImage);
                     
                     JPanel cellPanel = new JPanel();
                     cellPanel .setLayout (new GridBagLayout ());
                     GridBagConstraints gbc = new GridBagConstraints();
                     
                     JLabel space = new JLabel("    ");
                     space .setSize(80,20);
                     
                     JLabel label = new JLabel(product_name);
                     label .setSize(80,20);
                     label .setFont(new Font("tahoma", Font.BOLD, 15));
                     
                     gbc .insets = new Insets (5, 5, 0, 0);
                     
                     JButton buttonImage  = new JButton (productPicture);
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
                     spaceRight.setFont(new Font("tahoma", Font.BOLD, 80));
                     gbc .gridx = 4;
                     gbc .gridy = 0;
                     cellPanel .add (spaceRight , gbc );
                     
                     
                     JLabel loanIdLabel  = new JLabel ("Loan ID: "+loan_id);
                     loanIdLabel .setBackground(Color.BLUE);
                     loanIdLabel .setFont(new Font("verdana", Font.PLAIN, 15));
                     gbc .gridx = 4;
                     gbc .gridy = 1;
                     cellPanel .add (loanIdLabel , gbc );
                     
                     
                     JLabel returnLabel  = new JLabel ("Return Date: "+returndate);
                     returnLabel .setBackground(Color.BLUE);
                     returnLabel .setFont(new Font("verdana", Font.BOLD, 15));
                     gbc .gridx = 4;
                     gbc .gridy = 2;
                     cellPanel .add (returnLabel , gbc );
                     
                     buttonImage .addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             JOptionPane.showMessageDialog(null,"Product Code: "+product_code+"\n"
                                     + "Details: \n"+details);
                         }
                     });
                     
                     
                     resultPanel.add(space);
                     resultPanel.add(label);
                     resultPanel.add(cellPanel); 
                     
                           // Without following 2 lines, result will show after minimizing the window
                     resultPanel.revalidate();
                     resultPanel.repaint();
                     
                 }
                 
                     rsProduct.close();
                     stmtProduct.close();
                 
             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
       
       
       
                 
                 JScrollPane scrollPaneActiveLoan = new JScrollPane(resultPanel);
                 scrollPaneActiveLoan.setBounds(1300,300,600,600);
                 panel.add(scrollPaneActiveLoan);
                
    }
}
