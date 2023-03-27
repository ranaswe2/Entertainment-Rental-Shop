
package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ReturnLoan {
    
    int days;
    int loanId;
    String itemName;
    int lateDays;
    Statement stmt;
    int availability;
    int product_code;
    int payment;
    int stock;

    public ReturnLoan(int loanId) throws SQLException {
        
         stmt = MySQL.connection.createStatement();
         
         ResultSet rs = stmt.executeQuery("SELECT loan.product_code, product.item_name,product.stock,product.available, payment, days FROM loan"
                 + " inner join product on loan.product_code = product.product_code"
                 + " where loan_id = "+loanId);
         if(rs.next()) {
            this.itemName =  rs.getString("item_name");
            this.days= rs.getInt("days");
            this.availability= rs.getInt("available");
            this.product_code= rs.getInt("product_code");
            this.payment= rs.getInt("payment");
            this.stock= rs.getInt("stock");
         }
         this.loanId=loanId;
         
         rs.close();
        
    }

    public String getItemName() {
        return itemName;
    }


    public int getDays() {
        return days;
    }
    
    
    public int getLateDays() throws SQLException {
        int difference=0;
        
        
         ResultSet rs = stmt.executeQuery("SELECT DATEDIFF(now(), approve_date) AS date_difference from loan WHERE loan_id = "+loanId);
         if(rs.next()) {
            difference =  rs.getInt("date_difference");
         }
         rs.close();
        
         if(difference>days){
             lateDays= difference-days;
         }
         else{
             lateDays= 0;
         }
        
        return lateDays;
    }
    
    public void receiveLoan(int loanId,double payment,String flag) throws SQLException{
        int successProduct=0;
                if(this.payment==0.0){
                    if(flag.equals("Return")){
                                PreparedStatement pstmtProduct = MySQL.connection.prepareStatement("UPDATE product SET available = ? WHERE product_code = ?");
                                pstmtProduct.setInt(1, (availability+1)); 
                                pstmtProduct.setInt(2, product_code);

                                successProduct= pstmtProduct.executeUpdate();
                                pstmtProduct.close();
                    }
                    else if(flag.equals("Missing")){
                                PreparedStatement pstmtProduct = MySQL.connection.prepareStatement("UPDATE product SET stock = ? WHERE product_code = ?");
                                pstmtProduct.setInt(1, (stock-1)); 
                                pstmtProduct.setInt(2, product_code);

                                successProduct= pstmtProduct.executeUpdate();
                                pstmtProduct.close();
                    }
   
    
    
                                PreparedStatement pstmtLoan = MySQL.connection.prepareStatement("UPDATE loan SET return_date = now(), payment = ? WHERE loan_id = ?");
                                pstmtLoan.setDouble(1, payment); 
                                pstmtLoan.setInt(2, loanId);

                                int successLoan= pstmtLoan.executeUpdate();
                                pstmtLoan.close();
    
                                if(successProduct>0 && successLoan>0){
                                    JOptionPane.showMessageDialog(null, "Payment is accepted and\n the loan is repaid.");
                            }
                }
                else{
                       JOptionPane.showMessageDialog(null, "This loan has repaid once.\nSo, you can't receive it twice.");
                            }
    
    }
    
    
    
    
}
