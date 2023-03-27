
package report;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import database.MySQL;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class ItemReport {

    public ItemReport(String path, String itemName)  throws SQLException {
        
        
         Statement stmt = MySQL.connection.createStatement();
         
        
        LocalDate currentDate = LocalDate.now();
        Document document= new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path+"Item report-"+currentDate+".pdf"));
            document.open();
            
            Font normal= FontFactory.getFont(FontFactory.HELVETICA, 15, BaseColor.BLACK);
            Font bold= FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLUE);
            Font color= FontFactory.getFont(FontFactory.HELVETICA, 15, BaseColor.BLUE);
            
            Paragraph p= new Paragraph("Entertainment Rental Shop".toUpperCase(),bold); 
            p.setAlignment(Element.ALIGN_CENTER);
            Paragraph p1= new Paragraph("ITEM REPORT ON "+itemName.toUpperCase());
            p1.setAlignment(Element.ALIGN_CENTER);
            Paragraph p2=new Paragraph("Date "+currentDate);
            p2.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph p5=new Paragraph("     \n        ");
            
            document.add(p5);
            document.add(p);
            document.add(p1);
            document.add(p2);
            document.add(p5);    // Empty space
            document.add(p5);
            
            
            Paragraph p0=new Paragraph("                           Current Pricing",color);
            document.add(p0);
            
         ResultSet Item = stmt.executeQuery("SELECT fare_fee, late_fee, lost_fee FROM item where item_name = '"+itemName+"'");
         
         while(Item.next()) {
            double fare_fee= Item.getDouble("fare_fee"); 
            double late_fee= Item.getDouble("late_fee"); 
            double lost_fee= Item.getDouble("lost_fee"); 
            
            Paragraph p6= new Paragraph("                                  Fare Fee         :                      £"+fare_fee ,normal);
            Paragraph p7= new Paragraph("                                  Late Fee         :                      £"+late_fee ,normal);
            Paragraph p8= new Paragraph("                                  Missing Price  :                      £"+lost_fee ,normal);
           
          document.add(p6);
          document.add(p7);
          document.add(p8);
         }
         
         
            document.add(p5);
            document.add(p5);
            
            Paragraph p12=new Paragraph("                           Current Product",color);
            document.add(p12);
            
          ResultSet rsTotal = stmt.executeQuery("SELECT sum(stock) as total_copy, sum(available) as available_copy FROM product where item_name = '"+itemName+"'");
          
         while(rsTotal.next()) {
            int total_copy= rsTotal.getInt("total_copy");
            int available_copy= rsTotal.getInt("available_copy");
            int loaned_copy= total_copy-available_copy;
            
            Paragraph p9= new Paragraph("                                  Available Copy  :                    "+ available_copy,normal);
            Paragraph p10= new Paragraph("                                  Copy on Loan   :                    "+ loaned_copy,normal);
            Paragraph p11= new Paragraph("                                  Total Copy        :                    "+ total_copy,normal);
            
            document.add(p9);
            document.add(p10);
            document.add(p11);
         }
         rsTotal.close();
         
         
            document.add(p5);
            document.add(p5);
            document.add(p5);
            document.add(p5);
            
          ResultSet rsPopular = stmt.executeQuery("SELECT MIN(DATE_ADD(loan.approve_date, INTERVAL loan.days DAY)) AS earliest_return_date" +
                 " FROM product" +
                 " JOIN loan ON product.product_code = loan.product_code" +
                 " WHERE product.item_name = '"+itemName+"';");
          
         while(rsPopular.next()) {
            String earliest_return_date= rsPopular.getString("earliest_return_date");
            
            Paragraph p13= new Paragraph("Earliest Return Date:  "+earliest_return_date, color);
            p13.setAlignment(Element.ALIGN_CENTER);
            document.add(p13);
         }
         rsPopular.close();
         
         
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        document.close();
    }
    
    
}
