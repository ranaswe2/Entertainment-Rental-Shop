package userpanel;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import login.UserLogin;

public class Home extends User{
    public Home() {
        
        this.topBanner("Home");
        
        UserLogin login= new UserLogin();
        
        JLabel profilePictureLabel= new JLabel();
        profilePictureLabel.setBounds(450,200,200,250);
       
            ImageIcon imageIcon= new ImageIcon(login.getPhoto());
            Image image= imageIcon.getImage();
            Image resizedImage= image.getScaledInstance(profilePictureLabel.getWidth(),profilePictureLabel.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon profilePicture= new ImageIcon(resizedImage);
            
        profilePictureLabel.setIcon(profilePicture);
        
        super.leftSideLabelDecoration("Name",login.getName(),500);
        super.leftSideLabelDecoration("Username",login.getUsername(),600);
        super.leftSideLabelDecoration("User ID",String.valueOf(login.getId()),700);

        panel.add(profilePictureLabel);
    }
}
