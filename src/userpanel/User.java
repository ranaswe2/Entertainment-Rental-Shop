package userpanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User extends JFrame {

    public JPanel panel;
    public Container container;


    public User() {

        this.setTitle("Entertainment Rental Shop");
        container= this.getContentPane();
        this.setLayout(null);

        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#051E3E"));

        JLabel label= new JLabel();
        label.setText("User Panel        ");
        label.setBounds(1200,20,800,90);
        label.setFont(new Font("Serif", Font.BOLD, 90));
        label.setForeground(Color.WHITE);
        label.setBackground(Color.BLACK);
        label.setOpaque(true);
        //  window.setTextFill(Color.web("#0076a3"));





        Icon imageHome = new ImageIcon("src/userpanel/images/home.png");
        JButton buttonHome = new JButton(imageHome);
        this.buttonDecoration(buttonHome,175,"Home");
        this.buttonBorder(buttonHome);

        buttonHome.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Home();
                dispose();
            }
        });




        Icon imageSearch = new ImageIcon("src/userpanel/images/search.png");
        JButton buttonSearch = new JButton(imageSearch);
        this.buttonDecoration(buttonSearch,325,"User Request");
        this.buttonBorder(buttonSearch);
        buttonSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new Search();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }
        });

        Icon imageCart = new ImageIcon("src/userpanel/images/cart.png");
        JButton buttonCart = new JButton(imageCart);
        this.buttonDecoration(buttonCart,475,"Add New Item" );
        this.buttonBorder(buttonCart);
        buttonCart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Cart();
                dispose();
            }
        });

        Icon imageLoanedItem = new ImageIcon("src/userpanel/images/loaned.png");
        JButton buttonLoanedItem = new JButton(imageLoanedItem);
        this.buttonDecoration(buttonLoanedItem,625,"Logout");
        this.buttonBorder(buttonLoanedItem);
        buttonLoanedItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LoanedItem();
                dispose();
            }
        });


        Icon imageLogout = new ImageIcon("src/userpanel/images/logout.png");
        JButton buttonLogout = new JButton(imageLogout);
        this.buttonDecoration(buttonLogout,775,"Logout");
        this.buttonBorder(buttonLogout);
        buttonLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Logout();
                dispose();
            }
        });



        panel.add(label);
        panel.add(buttonHome);
        panel.add(buttonLogout);
        panel.add(buttonCart);
        panel.add(buttonLoanedItem);
        panel.add(buttonSearch);

        Toolkit toolkit= Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();     // Get Device Screen Size


        panel.setSize(screenSize);
        container.add(panel);

        this.setSize(screenSize);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);         // Set the JFrame to be maximized
        this.setResizable(false);

    }

    private void buttonBorder(JButton button){


        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.LIGHT_GRAY);
                //  button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.white);
                //  button.setForeground(UIManager.getColor("Button.foreground"));
            }
        });
    }

    private void buttonDecoration(JButton button,int yPosition, String toolTipText){

        button.setBackground(Color.WHITE);
        button.setBorderPainted(false);
        button.setToolTipText(toolTipText);
        button.setBounds(30,yPosition,200,100);

    }

    public void topBanner(String title){

        JLabel banner= new JLabel();
        banner.setText(title);
        banner.setBounds(200,20,800,70);
        banner.setFont(new Font("verdana", Font.BOLD, 50));
        banner.setForeground(Color.green);

        panel.add(banner);

    }

    public void inputDecorator(JTextField textField, String textLabel,int y){
        JLabel fieldName= new JLabel();
        fieldName.setText(textLabel);
        fieldName.setBounds(400,y,400,30);
        fieldName.setFont(new Font("verdana", Font.BOLD, 30));
        fieldName.setForeground(Color.WHITE);

        textField.setBounds(800,y,300,40);
        textField.setFont(new Font("tahome", Font.PLAIN, 30));

        panel.add(fieldName);
        panel.add(textField);
    }

    public void buttonStructure(JButton button,int x,int y,int width,int height){
        button.setBackground(Color.ORANGE);
        button.setBounds(x,y,width,height);
        button.setFont(new Font("tahoma", Font.BOLD, 30));
        panel.add(button);
    }

    public void leftSideLabelDecoration(String leftLabel, String rightLabel, int y){
        JLabel left= new JLabel();
        left.setText(leftLabel);
        left.setBounds(350,y,300,40);
        left.setFont(new Font("verdana", Font.BOLD, 30));
        left.setForeground(Color.WHITE);

        JLabel right= new JLabel();
        right.setText(rightLabel);
        right.setBounds(650,y,550,40);
        right.setFont(new Font("tahome", Font.PLAIN, 30));
        right.setForeground(Color.WHITE);

        panel.add(left);
        panel.add(right);
    }

    public void paint(Graphics gp) {

        gp.setColor(Color.CYAN);
        super.paint(gp);
        Graphics2D graphics = (Graphics2D) gp;

        Line2D lineHorizontal = new Line2D.Float(1200, 150, 150, 220);
        graphics.draw(lineHorizontal);


        Line2D lineVertical = new Line2D.Float(1200, 150, 1200, 900);
        graphics.draw(lineVertical);
    }

    public static void main(String[] args) {
        new User();
    }

}