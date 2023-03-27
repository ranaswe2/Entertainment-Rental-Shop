package signup;


import login.UserLoginWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class SignupWindow implements ActionListener {

    private JLabel window,nameLabel,usernameLabel,passwordLabel,repasswordLabel,imageLabel,loginLink,instruction;
    private JFrame frame;
    private JButton loginButton,imageButton;
    private JPanel panel;
    private JTextField usernameTextField,nameTextField;
    private JPasswordField passwordTextField,repasswordTextField;
    private Container container;
    String path;





    public SignupWindow() {

        frame= new JFrame("Entertainment Rental Shop");
        container= frame.getContentPane();
        frame.setLayout(null);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#051E3E"));


        window= new JLabel();
        window.setText("Registration");
        window.setBounds(25, 100, 400,  50);
        window.setFont(new Font("Serif", Font.BOLD, 44));
        window.setForeground(Color.WHITE);



        imageLabel= new JLabel("Select PNG, JPG & JPEG File only");
        imageLabel.setBounds(300, 50, 200,  250);
        imageLabel.setForeground(Color.WHITE);

        imageButton = new JButton("Profile Picture");
        imageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imageButton.setFont(new Font("Serif", Font.BOLD, 20));
        imageButton.setBackground(Color.GRAY);
        imageButton.addActionListener(this);
        imageButton.setBounds(300, 300, 200,  40);
        imageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser filecsr= new JFileChooser();
                filecsr.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter= new FileNameExtensionFilter("*.IMAGE","jpg","png","jpeg");
                filecsr.addChoosableFileFilter(filter);
                int result= filecsr.showSaveDialog(null);   // changed parameter from this to null
                if(result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = filecsr.getSelectedFile();
                    path = selectedFile.getAbsolutePath();

                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(new File(path));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    // Resize the image
                    int width = 200;
                    int height = 250;
                    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                    imageLabel.setIcon(new ImageIcon(scaledImage));

                }

             }
        });

        nameLabel= new JLabel();
        nameLabel.setText("Name ");
        nameLabel.setBounds(50, 375, 200,  40);
        nameLabel.setForeground(Color.WHITE);

        nameTextField = new JTextField(24);
        nameTextField.setText("");
        nameTextField.setEditable(true);
        nameTextField.setBounds(300, 375, 200,  40);
        nameTextField.setFont(new Font("Serif", Font.PLAIN, 18));


        usernameLabel= new JLabel();
        usernameLabel.setText("Username *");
        usernameLabel.setBounds(50, 450, 200,  40);
        usernameLabel.setForeground(Color.WHITE);


        usernameTextField = new JTextField(24);
        usernameTextField.setText("");
        usernameTextField.setEditable(true);
        usernameTextField.setBounds(300, 450, 200,  40);
        usernameTextField.setFont(new Font("Serif", Font.PLAIN, 18));


        passwordLabel= new JLabel();
        passwordLabel.setText("Password");
        passwordLabel.setBounds(50, 525, 200,  40);
        passwordLabel.setForeground(Color.WHITE);


        passwordTextField = new JPasswordField(24);
        passwordTextField.setText("");
        passwordTextField.setEditable(true);
        passwordTextField.setBounds(300, 525, 200,  40);

        repasswordLabel= new JLabel();
        repasswordLabel.setText("Retype Password");
        repasswordLabel.setBounds(50, 600, 200,  40);
        repasswordLabel.setForeground(Color.WHITE);


        repasswordTextField = new JPasswordField(24);
        repasswordTextField.setText("");
        repasswordTextField.setEditable(true);
        repasswordTextField.setBounds(300, 600, 200,  40);



        loginButton = new JButton("Sign Up");
        loginButton.setFont(new Font("Serif", Font.BOLD, 24));
        loginButton.setBackground(Color.ORANGE);
        loginButton.addActionListener(this);
        loginButton.setBounds(300, 675, 200,  40);


        loginLink= new JLabel();
        loginLink.setText("Already Have an Account? Click Here for Login Now !!!");
        loginLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLink.setBounds(150, 750, 320,  40);
        loginLink.setForeground(Color.LIGHT_GRAY);
        loginLink.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                new UserLoginWindow();
                frame.dispose();
            }
        });


        instruction= new JLabel();
        instruction.setText("* Username should be a unique single text like email, phone number or else");
        instruction.setBounds(75, 775, 500,  40);
        instruction.setForeground(Color.WHITE);

        panel.add(window);
        panel.add(imageLabel);
        panel.add(nameLabel);
        panel.add(usernameLabel);
        panel.add(passwordLabel);
        panel.add(repasswordLabel);
        panel.add(loginLink);
        panel.add(instruction);

        panel.add(usernameTextField);
        panel.add(nameTextField);
        panel.add(passwordTextField);
        panel.add(repasswordTextField);
        panel.add(loginButton);
        panel.add(imageButton);
        panel.setSize(600,900);
        container.add(panel);

        frame.setSize(600,900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource()==loginButton){

            String name= nameTextField.getText().trim();
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            String repassword = repasswordTextField.getText();

            try {
            Signup signup= new Signup(name,username,password,repassword,path);
                signup.execute();
                frame.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }



    public static void main(String[] args) {
        new SignupWindow();                      //Constructor
    }
}