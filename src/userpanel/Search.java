package userpanel;


import database.Item;
import database.MySQL;
import database.Reserve;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import login.UserLogin;

public class Search extends User{
    public Search() throws SQLException {

        Item list= new Item();
        String itemList[] = list.getItemList();
        UserLogin login= new UserLogin();

/************************** Search ************************************/

        JLabel select= new JLabel("Select Item");
        select.setBounds(400,300,400,30);
        select.setFont(new Font("verdana", Font.BOLD, 30));
        select.setForeground(Color.WHITE);
        panel.add(select);

        JComboBox item= new JComboBox(itemList);
        item.setBounds(800,300,300,50);
        item.setFont(new Font("tahoma", Font.BOLD, 30));
        panel.add(item);

        JTextField searchBox= new JTextField();
        super.inputDecorator(searchBox,"Enter Text",400);


        JButton searchButton= new JButton("Search Product");
        super.buttonStructure(searchButton,800,500,300,50);


/****************************************       Result        ************************************/

                 JLabel resultLabel= new JLabel("Results");     // To be hidden before clicking search button
                 resultLabel.setBounds(1480,150,400,50);
                 resultLabel.setFont(new Font("verdana", Font.BOLD, 50));
                 resultLabel.setForeground(Color.GREEN);
                 panel.add(resultLabel);
                 
                 
                 JPanel resultPanel= new JPanel();
                 BoxLayout layout = new BoxLayout(resultPanel, BoxLayout.Y_AXIS);
                 resultPanel.setLayout(layout);
                 
                 
      searchButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             
             try {
                 
                 resultPanel.removeAll();  // Without this line, previous searched result will appear on the screen
                 
                 String itemName= item.getSelectedItem().toString();
                 String text= searchBox.getText().trim();
                 String[] search= text.split(" ");
                 
                 Statement stmtProduct = MySQL.connection.createStatement();
                 ResultSet rsProduct = stmtProduct.executeQuery("SELECT product_code,product_name,item_name, details, stock, available, image FROM product where item_name='"+itemName+"' and UPPER(product_name) LIKE UPPER('%"+search[0]+"%')");
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
                     
                     JButton buttonCart = new JButton ("Add to Cart");
                     buttonCart .setBackground(Color.ORANGE);
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
                             reserve.addToCart();
                              
                             JOptionPane.showMessageDialog(null,"Product is added to the Cart.");
                             
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
       
       
                    }
                });
       
                 
                 JScrollPane scrollPane = new JScrollPane(resultPanel);
                 scrollPane.setBounds(1300,250,600,700);
                 panel.add(scrollPane);
                
    }
}
