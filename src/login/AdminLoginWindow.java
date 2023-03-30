package login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class AdminLoginWindow implements ActionListener {

    private JLabel window,usernameLabel,passwordLabel;
    private JFrame frame;
    private JButton loginButton;
    private JPanel panel;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private Container container;
    //setLayout(new GridBagLayout());





    public AdminLoginWindow() {
        frame= new JFrame("Entertainment Rental Shop");
        container= frame.getContentPane();
        frame.setLayout(null);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#051E3E"));

        window= new JLabel();
        usernameLabel= new JLabel();
        passwordLabel= new JLabel();

        loginButton = new JButton("Login");
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setFont(new Font("Serif", Font.BOLD, 24));
        loginButton.setBackground(Color.ORANGE);

        usernameTextField = new JTextField(24);
        passwordTextField = new JPasswordField(24);

        loginButton.addActionListener(this);

        // GridBagConstraints g = new GridBagConstraints();





        window.setText("Admin Panel");
        window.setBounds(170, 50, 400,  40);
        window.setFont(new Font("Serif", Font.BOLD, 44));
        window.setForeground(Color.WHITE);

        passwordLabel.setText("User Name: ");
        passwordLabel.setBounds(50, 200, 200,  40);
        passwordLabel.setForeground(Color.WHITE);

        usernameLabel.setText("Password:");
        usernameLabel.setBounds(50, 275, 200,  40);
        usernameLabel.setForeground(Color.WHITE);


        usernameTextField.setText("");
        usernameTextField.setEditable(true);
        usernameTextField.setBounds(300, 200, 200,  40);
        usernameTextField.setFont(new Font("Serif", Font.PLAIN, 18));
        usernameTextField.addKeyListener(new KeyAdapter(){
            
            public void keyTyped(KeyEvent e){
                 
                char ch= e.getKeyChar();
               
                if(!(ch >='a' && ch <='z' || ch >='1' && ch <='9'  ||(ch==KeyEvent.VK_BACK_SPACE) || (ch==KeyEvent.VK_PERIOD)|| (ch==KeyEvent.VK_DELETE))){
                     e.consume();
                    JOptionPane.showMessageDialog(null, " Enter only a-z, 1-9, '.' ");
                   
                }
             
            }
        });

        passwordTextField.setText("");
        passwordTextField.setEditable(true);
        passwordTextField.setBounds(300, 275, 200,  40);


        loginButton.setBounds(300, 400, 200,  40);

        panel.add(window);
        panel.add(usernameLabel);
        panel.add(passwordLabel);

        panel.add(usernameTextField);
        panel.add(passwordTextField);
        panel.add(loginButton);
        panel.setSize(600,600);
        container.add(panel);

        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loginButton){

            String username = usernameTextField.getText().trim();
            String password = passwordTextField.getText();
            System.out.println(username+password);

            try {
                LoginContext.execute(new AdminLogin(username,password,frame));        // We called login method for Admin Login
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }



    public static void main(String[] args) {
        new AdminLoginWindow();                      //Constructor
    }
}