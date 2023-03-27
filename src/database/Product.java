
package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Product {
    
    public void insertIntoDB(String productName,String item,String description,int stock,String imagePath) throws Exception{
        
                        InputStream photo = new FileInputStream(new File(imagePath));
                                
                        try {
                           PreparedStatement pstmt = MySQL.connection.prepareStatement("INSERT INTO product(product_name, item_name, details, stock, available, image) VALUES  (?, ?, ?, ?, ?, ?)");
                           pstmt.setString(1, productName);
                           pstmt.setString(2, item);
                           pstmt.setString(3, description);
                           pstmt.setInt(4, stock);
                           pstmt.setInt(5, stock);
                           pstmt.setBlob(6, photo);
                            
                            int rowAffected= pstmt.executeUpdate();
                            if(rowAffected>0){
                               JOptionPane.showMessageDialog(null,"Product is added!");
                            }
                            
                            pstmt.close();
                            
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
    }
    
}
