package start;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import login.AdminLoginWindow;
import login.LoginContext;
import login.UserLogin;
import login.UserLoginWindow;
import signup.SignupWindow;

public class CombinedFrame {
    

    private JLabel window,usernameLabel,passwordLabel,signupLink,loginLink,adminLink;
    private JFrame frame;
    private JPanel panel;
    private Container container;
    //setLayout(new GridBagLayout());





    public CombinedFrame() {
        frame= new JFrame("Entertainment Rental Shop");
        container= frame.getContentPane();
        frame.setLayout(null);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#051E3E"));

        
        adminLink= new JLabel();
        adminLink.setFont(new Font("Serif", Font.BOLD, 20));
        adminLink.setText("Admin Panel >>>");
        adminLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        adminLink.setBounds(1000, 20, 200,  40);
        adminLink.setForeground(Color.WHITE);
        adminLink.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                new AdminLoginWindow();
                frame.dispose();
            }
        });
        
        
        
        Icon logo = new ImageIcon("src/start/logo.png");
        window= new JLabel(logo);
        window.setBounds(100,100,1000,623);
        
        usernameLabel= new JLabel();
        passwordLabel= new JLabel();




        signupLink= new JLabel();
        signupLink.setFont(new Font("Serif", Font.BOLD, 20));
        signupLink.setText("Don't Have an Account?  SIGN UP NOW !!!");
        signupLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signupLink.setBounds(80, 750, 400,  40);
        signupLink.setForeground(Color.WHITE);
        signupLink.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                new SignupWindow();
                frame.dispose();
            }
        });


        loginLink= new JLabel();
        loginLink.setFont(new Font("Serif", Font.BOLD, 20));
        loginLink.setText("Already Have an Account?  LOG IN HERE !!!");
        loginLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLink.setBounds(700, 750, 400,  40);
        loginLink.setForeground(Color.WHITE);
        loginLink.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                new UserLoginWindow();
                frame.dispose();
            }
        });

        panel.add(window);
        panel.add(usernameLabel);
        panel.add(adminLink);
        panel.add(signupLink);
        panel.add(loginLink);

        panel.setSize(1200,923);
        container.add(panel);

        frame.setSize(1200,923);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }



   



    public static void main(String[] args) {
        new CombinedFrame();                      //Constructor
    }
}

