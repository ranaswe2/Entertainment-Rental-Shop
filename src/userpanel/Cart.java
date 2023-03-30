package userpanel;

import database.MySQL;
import database.Reserve;
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
import login.UserLogin;

public class Cart  extends User{
    public Cart() {
        super.topBanner("Reserved Products");
        
        
        UserLogin login= new UserLogin();

/****************************************       Result        ************************************/

                 JLabel resultLabel= new JLabel("Results");     // To be hidden before clicking search button
                 resultLabel.setBounds(400,200,400,40);
                 resultLabel.setFont(new Font("verdana", Font.BOLD, 40));
                 resultLabel.setForeground(Color.GREEN);
                 panel.add(resultLabel);
                 
                 
                 JPanel resultPanel= new JPanel();
                 BoxLayout layout = new BoxLayout(resultPanel, BoxLayout.Y_AXIS);
                 resultPanel.setLayout(layout);
                 
                 
             
             try {
                 
                 resultPanel.removeAll();  // Without this line, previous searched result will appear on the screen
                 
                 
                 Statement stmtProduct = MySQL.connection.createStatement();
                 ResultSet rsProduct = stmtProduct.executeQuery("SELECT reserved.product_code,product_name,item_name, details, stock, available, image "
                         + " FROM product"
                         + " join reserved on product.product_code = reserved.product_code"
                         + " where user_id="+login.getId());
                 // We just take the first string from the search text to search a product
                 while (rsProduct.next()) {
                     int product_code = rsProduct.getInt("product_code");
                     int total = rsProduct.getInt("stock");
                     int available = rsProduct.getInt("available");
                     String product_name = rsProduct.getString("product_name");
                     String item_name = rsProduct.getString("item_name");
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
                     spaceRight.setFont(new Font("tahoma", Font.BOLD, 50));
                     gbc .gridx = 4;
                     gbc .gridy = 0;
                     cellPanel .add (spaceRight , gbc );
                     
                     JButton buttonLoan  = new JButton ("Take a Loan");
                     buttonLoan .setBackground(Color.ORANGE);
                     buttonLoan .setFont(new Font("verdana", Font.PLAIN, 15));
                     gbc .gridx = 4;
                     gbc .gridy = 1;
                     cellPanel .add (buttonLoan , gbc );
                     
                     JButton buttonCart = new JButton ("Remove Product");
                     buttonCart .setBackground(Color.red);
                     buttonCart .setFont(new Font("verdana", Font.PLAIN, 15));
                     gbc .gridx = 4;
                     gbc .gridy = 2;
                     cellPanel .add (buttonCart , gbc );
                     
                     
                     
                     buttonImage .addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             JOptionPane.showMessageDialog(null,"Product Code: "+product_code+"\n"
                                     + "Available Copy: "+available+"\nDetails: \n"+details);
                         }
                     });
                     
                     buttonLoan .addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             new LoanRequestWindow(item_name, product_code);
                         }
                     });
                     
                     buttonCart .addActionListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent e) {
                             try {
                                 
                             Reserve reserve= new Reserve(login.getId(),product_code);
                             reserve.removeFromCart();
                              
                             JOptionPane.showMessageDialog(null,"Product is removed to the Cart.");
                             
                             } catch (SQLException ex) {
                                 ex.printStackTrace();
                             }
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
       
       
       
                 
                 JScrollPane scrollPane = new JScrollPane(resultPanel);
                 scrollPane.setBounds(400,300,600,700);
                 panel.add(scrollPane);
                
    }
}

    

