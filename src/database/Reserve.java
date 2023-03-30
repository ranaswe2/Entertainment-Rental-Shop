package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Reserve {
    
    private int userId;
    private int productCode;

    public Reserve(int userId, int productCode) {
        this.userId = userId;
        this.productCode = productCode;
    }
    
    public void addToCart() throws SQLException{
        
         PreparedStatement pstmt = MySQL.connection.prepareStatement("INSERT INTO reserved (user_id, product_code) VALUES (?, ?)");
         pstmt.setInt(1, userId);
         pstmt.setInt(2, productCode);
         
         pstmt.executeUpdate();
         pstmt.close();
    }
    
    
    public void removeFromCart() throws SQLException{
        
        PreparedStatement pstmtItem = MySQL.connection.prepareStatement("DELETE FROM reserved WHERE user_id= ? and product_code = ?");
                      pstmtItem.setInt(1, userId);  
                      pstmtItem.setInt(2, productCode); 
                      
                      pstmtItem.executeUpdate();
                      pstmtItem.close();
    }
}
