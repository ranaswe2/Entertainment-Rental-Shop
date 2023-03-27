package signup;

import database.MySQL;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class Signup {
    String name;
    String username;
    String password;
    String retypePassword;
    String imagePath;

    public Signup(String name, String username, String password, String retypePassword, String imagePath) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.retypePassword = retypePassword;
        this.imagePath = imagePath;
    }


    public void execute() throws Exception{

           InputStream photo = new FileInputStream(new File(imagePath));
           
                 if(password.equals(retypePassword)){
                     
                        PreparedStatement pst= MySQL.connection.prepareStatement("INSERT INTO `user`(`name`, `username`, `password`, `photo`) VALUES (?,?,?,?)" );
                        
                        pst.setString(1, name);
                        pst.setString(2, username);
                        pst.setString(3, password);
                        pst.setBlob(4, photo);
                        
                        pst.executeUpdate();
                        pst.close();
                        MySQL.connection.close();
            
                        
                        JOptionPane.showMessageDialog(null, "Your Information is Recorded.\n"
                                + "You need an admin approval to complete the registration.\n "
                                + "It is more better to visit the shop and meet with an admin.");
                 }
    }
}
