
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemPrice {
    double fairPrice;
    double lateFee;
    double missingFee;

    public ItemPrice(String itemName) throws SQLException {
        
         Statement stmt = MySQL.connection.createStatement();
         
         ResultSet rs = stmt.executeQuery("SELECT fare_fee, late_fee, lost_fee FROM item WHERE item_name='"+itemName+"'");

         if (rs.next()) {
             
        this.fairPrice = rs.getDouble("fare_fee");
        this.lateFee = rs.getDouble("late_fee");
        this.missingFee = rs.getDouble("lost_fee");
         }

         rs.close();
         stmt.close();
        
    }

    public double getFairPrice() {
        return fairPrice;
    }

    public double getLateFee() {
        return lateFee;
    }

    public double getMissingFee() {
        return missingFee;
    }
    
    
    public double getTotalFairPrice(int days) {
        return fairPrice*days;
    }
    
}
