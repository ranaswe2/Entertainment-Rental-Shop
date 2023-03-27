
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Item {
    
    
    
    public void delete(String item) throws SQLException{
        PreparedStatement pstmt = MySQL.connection.prepareStatement("DELETE FROM item WHERE  item_name= ?");
                      pstmt.setString(1, item);   
                      pstmt.executeUpdate();
                      pstmt.close();
    }
    
    
    public void update(String itemName, double fareFee, double lateFee, double missingFee) throws SQLException{
        
         PreparedStatement pstmt = MySQL.connection.prepareStatement("UPDATE item SET fare_fee = ?, late_fee = ?, lost_fee = ? WHERE item_name = ?");
                           pstmt.setDouble(1, fareFee);
                           pstmt.setDouble(2, lateFee);
                           pstmt.setDouble(3, missingFee);
                           pstmt.setString(4, itemName);
                            
                            int rowAffected= pstmt.executeUpdate();
                            if(rowAffected>0){
                               JOptionPane.showMessageDialog(null,"Item "+itemName+" is updated!");
                            }
                            
                            pstmt.close();
    }
    
    public String[] getItemList() throws SQLException{
         
         ArrayList<String> list= new ArrayList<>();
         
         Statement stmt = MySQL.connection.createStatement();
         
         ResultSet rs = stmt.executeQuery("SELECT item_name FROM item");

         while (rs.next()) {
            String item_name = rs.getString("item_name");
            list.add(item_name);
         }

         rs.close();
         stmt.close();
         
         String []itemList = new String[list.size()];
         list.toArray(itemList);

        return itemList;         // Make the Item list to a String array and return it
    }
}
