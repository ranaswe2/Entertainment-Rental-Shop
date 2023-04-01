package adminpanel;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import login.AdminLogin;

public class AdminDetailsWindow  {

    private JLabel topBanner,idLabel,nameLabel,usernameLabel;
    private JFrame frame;
    private JButton exitButton;
    private JPanel panel;
    private Container container;
    //setLayout(new GridBagLayout());





    public AdminDetailsWindow() {
        frame= new JFrame("Entertainment Rental Shop");
        container= frame.getContentPane();
        frame.setLayout(null);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#051E3E"));

        topBanner= new JLabel();
        usernameLabel= new JLabel();
        nameLabel= new JLabel();
        idLabel= new JLabel();
        
        exitButton = new JButton("Close");
        exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exitButton.setFont(new Font("Serif", Font.BOLD, 24));
        exitButton.setBackground(Color.RED);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        
        });





        topBanner.setText("Admin Details");
        topBanner.setBounds(170, 50, 400,  40);
        topBanner.setFont(new Font("Serif", Font.BOLD, 44));
        topBanner.setForeground(Color.WHITE);

           idLabel.setText("ID                :  "+AdminLogin.getId());
        idLabel.setBounds(50, 200, 500,  40);
        idLabel.setForeground(Color.WHITE);
        idLabel.setFont(new Font("Serif", Font.BOLD, 24));

        nameLabel.setText("Name          :  "+AdminLogin.getName());
        nameLabel.setBounds(50, 275, 500,  40);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Serif", Font.BOLD, 24));
        
            usernameLabel.setText("User Name :  "+AdminLogin.getUsername());
        usernameLabel.setBounds(50, 350, 500,  40);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Serif", Font.BOLD, 24));




        exitButton.setBounds(300, 450, 200,  40);

        panel.add(topBanner);
        panel.add(usernameLabel);
        panel.add(idLabel);
        panel.add(nameLabel);

        panel.add(exitButton);
        panel.setSize(600,600);
        container.add(panel);

        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



    public static void main(String[] args) {
        new AdminDetailsWindow();                      //Constructor
    }
}