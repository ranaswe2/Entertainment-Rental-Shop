
package database;

import java.awt.Image;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Loan {
    
    private String borrower;
    private int totalLoan;
    private int activeLoan;
    private int repaidLoan;
    private double paidAmount;
    private byte[] photo;

    public Loan(int user_id) throws SQLException {
        
         Statement stmt = MySQL.connection.createStatement();
         
         ResultSet rsName = stmt.executeQuery("SELECT name, photo FROM user where user_id = "+user_id);
         if(rsName.next()) {
            this.borrower =  rsName.getString("name");
            this.photo = rsName.getBytes("photo");
         }
         rsName.close();
         
         ResultSet rsLoan = stmt.executeQuery("SELECT COUNT(loan_id) as totalLoan ,COUNT(payment) as repaidLoan, SUM(payment) as paidAmount FROM `loan` WHERE user_id = "+user_id+" and isapproved = true");
         
         if(rsLoan.next()) {
             
             
        this.totalLoan = rsLoan.getInt("totalLoan");
        this.repaidLoan = rsLoan.getInt("repaidLoan");
        this.activeLoan = (totalLoan - repaidLoan);
        this.paidAmount = rsLoan.getDouble("paidAmount");
         }
         rsLoan.close();
         
         stmt.close();
        
    }
    
    public Loan(){}
    

    public String getBorrower() {
        return borrower;
    }

    public int getTotalLoan() {
        return totalLoan;
    }

    public int getActiveLoan() {
        return activeLoan;
    }

    public int getRepaidLoan() {
        return repaidLoan;
    }

    public double getPaidAmount() {
        return paidAmount;
    }
    
    public ImageIcon getBorrowerPhoto(){
        
            ImageIcon imageIcon= new ImageIcon(this.photo);
            Image image= imageIcon.getImage();
            Image resizedImage= image.getScaledInstance(200,250, Image.SCALE_SMOOTH);
            ImageIcon borrowerPicture= new ImageIcon(resizedImage);
            
            return borrowerPicture;
    }
    
    public void sendLoanRequest(int userID, int productCode, int days){
        
                        int availability=0;
        
                        try {
                           
                            Statement stmt = MySQL.connection.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT available FROM product where product_code = "+productCode);
         
                            if(rs.next()) {
                                availability =  rs.getInt("available");
                            }
                            rs.close();
                            
                            if(availability>0){
                                
                           PreparedStatement pstmtINSERT = MySQL.connection.prepareStatement("INSERT INTO loan(user_id, product_code, days) VALUES  (?, ?, ?)");
                           pstmtINSERT.setInt(1, userID);
                           pstmtINSERT.setInt(2, productCode);
                           pstmtINSERT.setInt(3, days);
                            
                            int rowAffected= pstmtINSERT.executeUpdate();
                            if(rowAffected>0){
                                
                                PreparedStatement pstmtUPDATE = MySQL.connection.prepareStatement("UPDATE product SET available = ? WHERE product_code = ?");
                                pstmtUPDATE.setInt(1, (availability-1)); 
                                pstmtUPDATE.setInt(2, productCode);

                                pstmtUPDATE.executeUpdate();
                                pstmtUPDATE.close();
                                
                                JOptionPane.showMessageDialog(null, "You need an admin approval to get this loan.\nGo to the shop and collect your product as soon as possible.");
                            }
                            
                            pstmtINSERT.close();
                            
                            }  
                            else{
                                JOptionPane.showMessageDialog(null, "Sorry!\nThis product is not available right now.\nPlease, wait some days.");
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
    }
    
    
}
