
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
import java.util.Date;

public class MonthlyReport {
   

    public MonthlyReport(String path) throws SQLException {
        
        
         Statement stmt = MySQL.connection.createStatement();
         
        
        LocalDate currentDate = LocalDate.now();
        Document document= new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path+"report-"+currentDate.getMonth()+".pdf"));
            document.open();
            
            Font normal= FontFactory.getFont(FontFactory.HELVETICA, 15, BaseColor.BLACK);
            Font bold= FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLUE);
            Font color= FontFactory.getFont(FontFactory.HELVETICA, 15, BaseColor.BLUE);
            
            Paragraph p= new Paragraph("Entertainment Rental Shop".toUpperCase(),bold); 
            p.setAlignment(Element.ALIGN_CENTER);
            Paragraph p1= new Paragraph("MONTHLY REPORT - "+currentDate.getMonth()+" "+currentDate.getYear());
            p1.setAlignment(Element.ALIGN_CENTER);
            Paragraph p2=new Paragraph("Date "+currentDate);
            p2.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph p5=new Paragraph("     \n        ");
            Paragraph p6=new Paragraph("Item Name (Loaned & Returned)",color);
            
            document.add(p5);
            document.add(p);
            document.add(p1);
            document.add(p2);
            document.add(p5);    // Empty space
            document.add(p5);
            document.add(p6);
            
            
         ResultSet Item = stmt.executeQuery("SELECT product.item_name,"
                 + " count(payment) as item_returned, count(loan_id) as item_loaned FROM loan"
                 + " inner join product on loan.product_code = product.product_code"
                 + " WHERE MONTH(loan.approve_date) = MONTH(NOW())"
                 + " GROUP BY product.item_name");
         
         while(Item.next()) {
            String itemName =  Item.getString("item_name");
            int item_returned= Item.getInt("item_returned");
            int item_loaned= Item.getInt("item_loaned");
            
            Paragraph p8= new Paragraph(itemName+" : Loaned  "+item_loaned+" & Returned "+item_returned ,normal);
           
          document.add(p8);
         }
         
         
            document.add(p5);
            document.add(p5);
            
          ResultSet rsTotal = stmt.executeQuery("SELECT count(payment) as total_returned, count(loan_id) as total_loaned FROM loan"
                  + " WHERE MONTH(loan.approve_date) = MONTH(NOW())");
         while(rsTotal.next()) {
            int total_returned= rsTotal.getInt("total_returned");
            int total_loaned= rsTotal.getInt("total_loaned");
            
            Paragraph p9= new Paragraph("Returned Loan: "+ total_returned,normal);
            Paragraph p10= new Paragraph("Active Loan: "+ (total_loaned-total_returned),color);
            Paragraph p11= new Paragraph("Total Loan: "+ total_loaned,normal);
            
            document.add(p9);
            document.add(p10);
            document.add(p11);
         }
         rsTotal.close();
         
            document.add(p5);
            document.add(p5);
            
          ResultSet rsPopular = stmt.executeQuery("SELECT product.item_name, COUNT(loan.loan_id) AS num_times_loaned" +
                 " FROM product" +
                 " LEFT JOIN loan ON product.product_code = loan.product_code"+
                 " WHERE MONTH(loan.approve_date) = MONTH(NOW())" +
                 " GROUP BY product.item_name" +
                 " ORDER BY num_times_loaned DESC" +
                 " LIMIT 1;");
          
         while(rsPopular.next()) {
            String item_name= rsPopular.getString("item_name");
            
            Paragraph p12= new Paragraph("Most Popular Item: "+ item_name,color);
            document.add(p12);
         }
         rsPopular.close();
         
         
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        document.close();
    }
    
    
}
