package login;

import signup.SignupWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;


public class UserLoginWindow implements ActionListener {

    private JLabel window,usernameLabel,passwordLabel,signupLink;
    private JFrame frame;
    private JButton loginButton;
    private JPanel panel;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private Container container;
    //setLayout(new GridBagLayout());





    public UserLoginWindow() {
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





        window.setText("User Panel");
        window.setBounds(180, 50, 400,  40);
        window.setFont(new Font("Serif", Font.BOLD, 44));
        window.setForeground(Color.WHITE);

        passwordLabel.setText("Username: ");
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
                    JOptionPane.showMessageDialog(null, " Enter only a-z, 1-9, '.'");
                   
                }
             
            }
        });

        passwordTextField.setText("");
        passwordTextField.setEditable(true);
        passwordTextField.setBounds(300, 275, 200,  40);


        loginButton.setBounds(300, 350, 200,  40);



        signupLink= new JLabel();
        signupLink.setText("Don't Have an Account? Click Here for Sign up Now !!!");
        signupLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signupLink.setBounds(150, 425, 320,  40);
        signupLink.setForeground(Color.LIGHT_GRAY);
        signupLink.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                new SignupWindow();
                frame.dispose();
            }
        });

        panel.add(window);
        panel.add(usernameLabel);
        panel.add(passwordLabel);
        panel.add(signupLink);

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

            String username = usernameTextField.getText();
            String password = passwordTextField.getText();

            try {
                LoginContext.execute(new UserLogin(username,password,frame));        // We called login method or Admin Login
               
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    }



    public static void main(String[] args) {
        new UserLoginWindow();                      //Constructor
    }
}