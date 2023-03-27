package adminpanel;

import database.Item;
import database.MySQL;
import database.Product;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewProduct extends Admin{

    String path,s;

    public NewProduct() throws SQLException, Exception {



        super.topBanner("Product");

/**************************    Select All Item & Make a List  *******************************/

        Item list= new Item();
        String itemList[] = list.getItemList();

        JLabel imageLabel= new JLabel("* Only PNG, JPG, and JPEG formats are acceptable");
        imageLabel.setBounds(800,350,400,30);
        imageLabel.setForeground(Color.WHITE);
        panel.add(imageLabel);

        JLabel imageBox= new JLabel("");
        imageBox.setBounds(450,200,200,250);
        panel.add(imageBox);



        JButton imageButton= new JButton("Choose Image");
        imageButton.setBounds(800,300,300,50);
        imageButton.setFont(new Font("tahoma", Font.BOLD, 30));
        imageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser filecsr= new JFileChooser();
                filecsr.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter= new FileNameExtensionFilter("*.IMAGE","jpg","png","jpeg");
                filecsr.addChoosableFileFilter(filter);
                int result= filecsr.showSaveDialog(null);   // changed parameter from this to null
                if(result == JFileChooser.APPROVE_OPTION){
                    File selectedFile= filecsr.getSelectedFile();
                    path= selectedFile.getAbsolutePath();

                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(new File(path));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    // Resize the image
                    int width = 200;
                    int height = 250;
                    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);


                    imageBox.setIcon(new ImageIcon(scaledImage));
                    s=path;
                }
            }
        });
        panel.add(imageButton);




        JTextField productName= new JTextField();
        super.inputDecorator(productName,"Product Name",500);

        JLabel select= new JLabel("Select Item");
        select.setBounds(400,600,400,30);
        select.setFont(new Font("verdana", Font.BOLD, 30));
        select.setForeground(Color.WHITE);
        panel.add(select);


        JComboBox item= new JComboBox(itemList);
        item.setBounds(800,600,300,50);
        item.setFont(new Font("tahoma", Font.BOLD, 30));
        panel.add(item);

        JTextField aboutProduct= new JTextField();
        super.inputDecorator(aboutProduct,"Product Details",700);

        JTextField stock= new JTextField();
        super.inputDecorator(stock,"Stock Size",800);



/**************************    Add Product to Database *******************************/

        JButton addProductButton= new JButton("Add Product");
        super.buttonStructure(addProductButton,800,900,300,50);
        addProductButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            String name=productName.getText().trim();
            String itemname=item.getSelectedItem().toString();
            String description=aboutProduct.getText().trim();
            int totalStock=Integer.parseInt(stock.getText().trim());
            
                try {
                    Product product= new Product();
                    product.insertIntoDB(name,itemname,description,totalStock,path);
                    
                    imageBox.setText("");
                    productName.setText("");
                    aboutProduct.setText("");
                    stock.setText("");     
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
       
/**************************************** View Product List ************************************/

        JLabel listLabel= new JLabel("Product List");     // To be hidden before clicking search button
        listLabel.setBounds(1450,170,600,50);
        listLabel.setFont(new Font("verdana", Font.BOLD, 40));
        listLabel.setForeground(Color.YELLOW);
        panel.add(listLabel);


        JPanel productPanel= new JPanel();
        BoxLayout productBoxLayout = new BoxLayout(productPanel, BoxLayout.Y_AXIS);
        productPanel.setLayout(productBoxLayout);


         Statement stmtProduct = MySQL.connection.createStatement();
         ResultSet rsProduct = stmtProduct.executeQuery("SELECT product_code, product_name, item_name, details, stock, available, image FROM product");

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
            ImageIcon profilePicture= new ImageIcon(resizedImage);
            
            
                 JPanel cellPanelProduct = new JPanel();
                 cellPanelProduct .setLayout (new GridBagLayout ());
                 GridBagConstraints gbcProduct  = new GridBagConstraints();


                JLabel spaceProduct = new JLabel("    ");
                spaceProduct .setSize(80,20);


                JLabel labelProductName = new JLabel(product_name);
                labelProductName .setSize(80,20);
                labelProductName .setFont(new Font("tahoma", Font.BOLD, 15));

                gbcProduct .insets = new Insets (5, 5, 0, 0);
 
                JButton buttonProductImage  = new JButton (profilePicture);
                buttonProductImage .setBackground(Color.WHITE);
                gbcProduct.gridx = 0;
                gbcProduct.gridy = 0;
                gbcProduct.gridwidth = 4;
                gbcProduct.gridheight = 4;
                gbcProduct.fill = GridBagConstraints.BOTH;
                cellPanelProduct .add (buttonProductImage, gbcProduct );

                // Reset to defaults.

                gbcProduct.gridwidth = 1;
                gbcProduct.gridheight = 1;
                gbcProduct.fill = GridBagConstraints.NONE;

                JLabel spaceRightProduct= new JLabel("    ");         // Horizontal Space
                spaceRightProduct.setFont(new Font("tahoma", Font.BOLD, 60));
                gbcProduct .gridx = 4;
                gbcProduct .gridy = 0;
                cellPanelProduct .add (spaceRightProduct , gbcProduct );


                JButton buttonRemove = new JButton ("Remove Product");
                buttonRemove .setBackground(Color.RED);
                buttonRemove .setFont(new Font("verdana", Font.PLAIN, 15));
                gbcProduct .gridx = 4;
                gbcProduct .gridy = 1;
                cellPanelProduct .add (buttonRemove , gbcProduct );






                buttonProductImage .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null,"Product Code: "+product_code+"\n"
                                + "Product in Stock: "+total+"\n"
                                + "Available Product: "+available+"\nProduct Description:\n"+details);
                    }
                });


                buttonRemove .addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        PreparedStatement pstmt;
                        try {
                            pstmt = MySQL.connection.prepareStatement("DELETE FROM product WHERE  product_code= ?");
                        
                            pstmt.setInt(1, product_code);   
                            pstmt.executeUpdate();
                            
                            buttonRemove.setText("Product Removed");
                            buttonRemove.setBackground(Color.GREEN);

                            pstmt.close();
                        
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        
                    }
                });

                productPanel.add(spaceProduct );
                productPanel.add(labelProductName );
                productPanel.add(cellPanelProduct );

            }


         rsProduct.close();
         stmtProduct.close();
        // con.close();

        JScrollPane scrollPane = new JScrollPane(productPanel);
        //   scrollPane.setBounds(1220,300,680,500);
        scrollPane.setBounds(1300,250,600,700);
       panel.add(scrollPane);
    }
}

